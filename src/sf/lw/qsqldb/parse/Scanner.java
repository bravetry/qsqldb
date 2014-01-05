package sf.lw.qsqldb.parse;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Locale;


public class Scanner {
	private String sqlString;
	private int currentPosition;
	int     tokenPosition;
	
    Token token=new Token();
    char[]          charBuffer = new char[256];
    CharArrayWriter charWriter = new CharArrayWriter(charBuffer);

	//空白字符
    static final char[] whitespace = {
        // SQL extras
        0x9,
        0xA,
        0xB,
        0xC,
        0xD,
        0x20,
        0x85,
        // U Zs
        0x0020,
        0x00A0,
        0x1680,
        0x180E,
        0x2000,
        0x2001,
        0x2002,
        0x2003,
        0x2004,
        0x2005,
        0x2006,
        0x2007,
        0x2008,
        0x2009,
        0x200A,
        0x202F,
        0x205F,
        0x3000,
        // U Zl
        0x2028,
        // U Zp
        0x2029,
    };
	static final HashSet whiteSpaceSet = new HashSet(32);
    static {
        for (int i = 0; i < whitespace.length; i++) {
            whiteSpaceSet.add(whitespace[i]);
        }
    }
    
    public void scanNext(){
    	 if(currentPosition==limit){
    		 //scanner状态重置
    		 token.tokenType=Tokens.X_ENDPARSE;//置上结束标志
    		 return;
    	 }
    	 //移动指针跳过空白
    	 scanSeparator();
    	 if(currentPosition==limit){
    		 //scanner状态重置
    		 token.tokenType=Tokens.X_ENDPARSE;//置上结束标志
    		 return;
    	 }
    	 //扫描标识
    	 scanToken();
    	 
    	 if(token.isMalformed){//对于错误的标识错误错误标识的子串
    		 token.fullString=sqlString.substring(tokenPosition,currentPosition);
    	 }
    }
    //跳过空白
    public void scanSeparator(){
    	scanWhitespace();
    	
    }
    
	private void scanToken() {
		int character = charAt(currentPosition);
		switch (character) {
		case '\'' :
            scanCharacterString();
            token.dataType = CharacterType.getCharacterType(Types.SQL_CHAR,token.tokenString.length());
            token.tokenType   = Tokens.X_VALUE;
            token.isDelimiter = true;
            return;
		case '?' :
            token.tokenString = Tokens.T_QUESTION;
            token.tokenType   = Tokens.QUESTION;
            currentPosition++;
            token.isDelimiter = true;
            return;
		case '=':
			token.tokenString=Tokens.T_EQUALS;
			token.tokenType=Tokens.EQUALS;
			token.isDelimiter=true;
			currentPosition++;
			return;
		case '-':
			//需要判断一下,当前位置的下一个位置是不是还是-如果是则进行错误处理,从截取字符串从当前位置+2到本行末尾(定位\r或\n) 
			token.tokenString=Tokens.T_MINUS;
			token.tokenType=Tokens.MINUS;
			token.isDelimiter=true;//这里为什么标识为分隔符号??????
			currentPosition++;
			return;
		case '0':
		case '1':
		case '2':
		case '3':
		case '4':
		case '5':
		case '6':
		case '7':
		case '8':
		case '9':
			token.tokenType = Tokens.X_VALUE;
			scanNamber();
			return;
		}
		//扫描标识符号
		scanIdentifierChain();
		//
		setIdentifierProperties();
	}
	//设置标识符
	private void setIdentifierProperties() {
        if (token.tokenType == Tokens.X_IDENTIFIER) {//标识符号
            token.isUndelimitedIdentifier = true;
            if (token.namePrefix == null) {
                token.tokenType = Tokens.getKeywordID(token.tokenString,
                                                      Tokens.X_IDENTIFIER);

                if (token.tokenType == Tokens.X_IDENTIFIER) {//预留字,函数,普通字符串
                    token.tokenType = Tokens.getNonKeywordID(token.tokenString,
                            Tokens.X_IDENTIFIER);
                } else {//保留字
                    token.isReservedIdentifier = true;
                    token.isCoreReservedIdentifier =Tokens.isCoreKeyword(token.tokenType);
                }
            }
        } else if (token.tokenType == Tokens.X_DELIMITED_IDENTIFIER) {//界定符,如:表示字符串的单引号,
            token.isDelimitedIdentifier = true;
        }
	}
	
	public boolean scanWhitespace() {
        boolean hasSpace=false;
		for (; currentPosition < limit; currentPosition++) {
            char c = sqlString.charAt(currentPosition);
            if(c==' '){
            	hasSpace=true;
            	continue;
            }
            if(whiteSpaceSet.contains(c)){
            	continue;
            }
            break;
        }
		return hasSpace;
	}
	
	private void scanIdentifierChain() {
		int c = charAt(currentPosition);

        switch (c) {
		    default :
		        boolean result = scanUndelimitedIdentifier();
                if (!result) {
                    return;
                }
                token.tokenType   = Tokens.X_IDENTIFIER;
                token.isDelimiter = false;
                
        }
        
        boolean hasPreSpace = scanWhitespace();
        c = charAt(currentPosition);
        
        //TODO 处理 *  . 以及前缀等
	}
	
	public boolean scanUndelimitedIdentifier() {
        if (currentPosition == limit) {
            return false;
        }
        //开始部分可以"_","$"和字符,---下划线和美元符号不是字符
        
        char start=sqlString.charAt(currentPosition);
        
        boolean irregular=start=='_'||start=='$';
        if(!irregular&&!Character.isLetter(start)){
        	token.tokenString=Character.toString(start);
        	token.tokenType=Tokens.X_UNKNOWN_TOKEN;
        	token.isMalformed=true;
        	return false;
        }
        
     
        int i = currentPosition+1;
        for(;i<limit;i++){
        	int c = charAt(i);
            if (c == '$') {
                irregular = true;//在首字符以外遇到$符号也被标识为不规则的了
                continue;
            }

            if (c == '_' || Character.isLetterOrDigit(c)) {
                continue;
            }
            break;
        }
        
        token.tokenString=sqlString.substring(currentPosition,i).toUpperCase(Locale.ENGLISH);
        currentPosition=i;
        if (irregular) {
            token.hasIrregularChar = true;
        }
        return true;
	}
	
	public void scanStringPart(char quoteChar) {
		currentPosition++;//跳过当前的符号
		int nextIndex=-1;
		for(;;){
			//注意:String的indexOf方法是搜索整个字符串,并不是在遇到回车换行符结束
			nextIndex=sqlString.indexOf(quoteChar,currentPosition);
			if(nextIndex<0){
				token.tokenString=sqlString.substring(currentPosition,limit);
				token.tokenType=quoteChar=='\'' ? Tokens.X_MALFORMED_STRING:Tokens.X_MALFORMED_IDENTIFIER;
				token.isMalformed=true;//获取字符串错了,
				return;
			}
			if(charAt(nextIndex+1)==quoteChar){//处理单引号转义的作用
				nextIndex+=1;
				//注意这里不是字符串截取子串,边界不要弄混,取出来包括nextIndex指示的单引号
				charWriter.write(sqlString, currentPosition,nextIndex-currentPosition);
				currentPosition=nextIndex+1;
				continue;
			}else{
				charWriter.write(sqlString, currentPosition,nextIndex-currentPosition);
				currentPosition=nextIndex+1;
				break;
			}			
		}
	}
	
	
	
	void scanCharacterString() {
		charWriter.reset(charBuffer);
		scanStringPart('\'');
		token.tokenString=charWriter.toString();
		token.tokenValue=token.tokenString;
	}
	
	public  void reset(String sql){
		this.sqlString=sql;
		this.limit=this.sqlString.length();
	}
	
	protected void scanNamber() {
		
		boolean hasDigit = false;
		boolean hasPoint = false;
		boolean     end  = false;
		
		int exponentIndex=-1;
		int tokenStart=currentPosition;
		
		token.tokenType = Tokens.X_VALUE;
		token.dataType  = Type.SQL_INTEGER;
		for (; currentPosition <= limit; currentPosition++) {
			end=false;
			int c = charAt(currentPosition);
			switch (c) {
			case '\'':
				scanCharacterString();
				if(token.isMalformed){
					return;
				}
				token.dataType=CharacterType.getCharacterType(Types.SQL_CHAR, token.tokenString.length());
				token.tokenType=Tokens.X_VALUE;
				return;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
                hasDigit=true;
                break;
			case '.':
				token.dataType  = Type.SQL_NUMERIC;
				if(hasPoint||exponentIndex!=-1 ){
					token.tokenString=sqlString.substring(tokenStart,currentPosition+1);
					token.tokenType=Tokens.X_MALFORMED_NUMERIC;
					token.isMalformed=true;
					return;
				}
				hasPoint=true;
				break;
			default:
				end=true;
				break;
		    
		   }
		   if(end){
			 break;   
		   }
		}
		
		token.tokenString=sqlString.substring(tokenStart,currentPosition);
		
		switch(token.dataType.typeCode){
		     case Types.SQL_INTEGER:
		    	  if(token.tokenString.length()<11){
			           token.tokenValue=ValuePool.getInt(Integer.parseInt(token.tokenString));
			           return;
		    	  }
		    	  if(token.tokenString.length()<20){
		    		  token.tokenValue=ValuePool.getLong(Long.parseLong(token.tokenString));
		    	  }
		    	  token.dataType = Type.SQL_NUMERIC;
		     case Types.NUMERIC:
		    	 try{
                    BigDecimal decimal = new BigDecimal(token.tokenString);
                    token.tokenValue = decimal;
		    	 }catch(Exception e){
					token.tokenType=Tokens.X_MALFORMED_NUMERIC;
					token.isMalformed=true;
					return;
		    	 }
		      return;	    
		}
	}

	int limit;

	private int charAt(int i) {
		if (i >= limit) {
			return -1;
		}
		return sqlString.charAt(i);
	}

}
