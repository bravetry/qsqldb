package sf.lw.qsqldb.parse;

public class Token {
   boolean isDelimitedIdentifier;
   boolean isUndelimitedIdentifier;
   boolean isReservedIdentifier;
   boolean isCoreReservedIdentifier;
   
   String  namePrefix;
   
   public int     tokenType;
   public Type    dataType;
   public Object  tokenValue;
   public boolean isMalformed=false;
   boolean hasIrregularChar;//比如,$,_
   public String tokenString;
   
   boolean    isDelimiter; //分隔符
   
   
   String  fullString;//在scanner.scanNext中记录错误标识串 
   
}
