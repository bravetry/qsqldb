package sf.lw.qsqldb.parse;

import java.sql.SQLException;


public class ParerDQL extends SqlParserBase {

    public  ParerDQL(Scanner scanner){
    	super(scanner);
    }
	
	//读取逻辑或表达式
	Expression XreadBooleanValueExpression_or(){
		Expression e=XreadBooleanTermOrNull_AND();
		if(e==null){
			throw new RuntimeException("读取表达式出错");
		}
		Expression a;
		while(true){
			if(token.tokenType==Tokens.OR){
				read();
				a=e;
				e=XreadBooleanTermOrNull_AND();
				if(e==null){
				    throw new RuntimeException("读取表达式出错");
				}
				e=new ExpressionLogical(OpTypes.OR, a, e);
			}else{
				break;
			}
		}
		return e;
	}
	
	//读取逻辑与表达式
	Expression XreadBooleanTermOrNull_AND() {
		Expression e=XreadBooleanFactorOrNull_not();
		if(e==null){
			return null;
		}
		Expression b;
		while(true){
			if(token.tokenType==Tokens.AND){
			   	
			}else{
				break;
			}
			
			read();
			b=e;
			e=XreadBooleanFactorOrNull_not();
			if(e==null){
			    throw new RuntimeException("非法token");
			}
			e=new ExpressionLogical(OpTypes.AND,b,e);
		}
		return e;
	}
	

	//读取逻辑非表达式
	//Factor布尔因子
	Expression XreadBooleanFactorOrNull_not(){
		boolean not=false;
		Expression e;
		if(token.tokenType==Tokens.NOT){
			read();
			not=true;
		}
		e=XreadBooleanTestOrNull();
		if(e==null){
		   return null;
		}
		
		if(not){
			e=new ExpressionLogical(OpTypes.NOT,e);
		}
		return e;
	}
	
	Expression XreadBooleanTestOrNull() {
		boolean unknown=false;
		boolean isNot=false;
		Expression e=XreadBooleanPrimaryOrNull();
		if(e==null){
			return null;
		}
		if(token.tokenType==Tokens.IS){
			read();
			if(token.tokenType==Tokens.NOT){
				read();
				isNot=true;
			}
			if(token.tokenType==Tokens.UNKNOWN){
				unknown=true;
			}else{
				throw new  RuntimeException("unexpectedToken");
			}
		}
		
		if(unknown){
			e=new ExpressionLogical(OpTypes.IS_NULL,e);
		}
		if(isNot){
			e=new ExpressionLogical(OpTypes.NOT,e);
		}
	    return e;		
	}
	
	Expression XreadBooleanPrimaryOrNull() {
		 Expression e;
		 switch (token.tokenType) {
		    default:
		      //TODO 这里暂时不进行异常处理	
              e = XreadAllTypesCommonValueExpression(false);
		 }
		 
		 if (e == null && token.tokenType == Tokens.OPENBRACKET) {
            //TODO
		 }
	     if (e != null) {
	          e = XreadPredicateRightPart(e);
	     }

	    return e;
	}
	Expression XreadPredicateRightPart(final Expression l) {
		Expression e = null;
		boolean hasNot=false;
        if (token.tokenType == Tokens.NOT) {
            read();
            hasNot = true;
        }
        //not 后是如下标识符号,LIKE,BETWEEN,IN是允许的
		switch(token.tokenType){
		    case Tokens.EQUALS:{
		         //TODO 获取类型
		    	 read();
		    	 switch(token.tokenType){
		    	    default:
                        Expression row = XreadAllTypesCommonValueExpression(false);
                        e = new ExpressionLogical(OpTypes.EQUAL, l, row);
                        break;
		    	 }
		         break;	
		    }
            default :
                if (hasNot) {//在这里一些特殊情况下,not是正确的
                    throw new RuntimeException("ss");
                }
                return l;
		}
        if (hasNot) {
            e = new ExpressionLogical(OpTypes.NOT, e);
        }
        
		return e;
	}
	
	
	Expression XreadAllTypesCommonValueExpression(boolean boole) {
		Expression e    = XreadAllTypesTerm(boole);
		return e;
		
	}
	
	Expression XreadAllTypesTerm(boolean boole) {
		Expression e    = XreadAllTypesFactor(boole);
		return e;
	}
	
	Expression XreadAllTypesFactor(boolean boole) {
		Expression e;
        boolean    minus   = false;
        boolean    not     = false;
        boolean    unknown = false;
        switch (token.tokenType) {
           case Tokens.MINUS :
               read();
	           boole = false;
	           minus = true;
	           break;
        }
		e = XreadAllTypesPrimary(boole);
        if (unknown) {
            e = new ExpressionLogical(OpTypes.IS_NULL, e);
        } else if (minus) {
            e = new ExpressionArithmetic(OpTypes.NEGATE, e);
        } else if (not) {
            e = new ExpressionLogical(OpTypes.NOT, e);
        }
	        
		return e;
	}
	//函数表达式和值表达式
	Expression XreadAllTypesPrimary(boolean boole) {
		Expression e;
		switch(token.tokenType){
	       default :
	          e = XreadAllTypesValueExpressionPrimary(boole);
		}
		return e;
	}
	Expression XreadAllTypesValueExpressionPrimary(boolean boole) {
	    Expression e = null;
        switch (token.tokenType) {
	        default :
	            e = XreadSimpleValueExpressionPrimary();
        }
        return e;
	}
    Expression XreadSimpleValueExpressionPrimary() {
        Expression e;
        e = XreadUnsignedValueSpecificationOrNull();
        if (e != null) {
            return e;
        }
        //TODO 其他复杂表达式的获取
        
        
        return e;
    }
	
    
	Expression XreadUnsignedValueSpecificationOrNull() {

		Expression e = null;
		switch (token.tokenType) {
	        case Tokens.TRUE :
	            read();
	            return Expression.EXPR_TRUE;
	        case Tokens.FALSE :
	            read();
                 return Expression.EXPR_FALSE;
            case Tokens.QUESTION :
                ExpressionColumn p =
                    new ExpressionColumn(OpTypes.DYNAMIC_PARAM);
//                compileContext.addParameter(p, getPosition());
                read();
                return p;
			case Tokens.NULL:
				e = new ExpressionValue(null, null);
				read();
				return e;
			case Tokens.X_VALUE:
				e = new ExpressionValue(token.tokenValue, token.dataType);
				read();
				return e;
		}
		return e;
	}
	
	public void readWhere(){
		read();
		Expression e = XreadBooleanValueExpression_or();
	}
	
	public static  void main(String arg[]) throws ClassNotFoundException, SQLException{
		ParerDQL parerDQL=new ParerDQL(new Scanner());
		parerDQL.scanner.reset("6=? and '1'=?  and -9=?");
		
		parerDQL.readWhere();
		
	}
	
	
	
}
