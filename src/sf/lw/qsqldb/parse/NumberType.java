package sf.lw.qsqldb.parse;









public class NumberType extends Type {
	static final int   integerPrecision             = 10;//java int
	static final int   bigintPrecision              = 19;//java long
	public static final int defaultNumericScale = 32;
	public static final int defaultNumericPrecision = 128;
	public static final int DECIMAL_WIDTH = 256;
	
	public static final int INTEGER_WIDTH  = 32;
	public static final int BIGINT_WIDTH   = 64;
	
    public static final Type SQL_NUMERIC_DEFAULT_INT =
        new NumberType(Types.NUMERIC, defaultNumericPrecision, 0);
    
    
	final int typeWidth;
	
	public NumberType(int type, long precision, int scale) {
		super(Types.SQL_NUMERIC, type, precision, scale);
		System.out.println(type);
		switch (type) {
	        case Types.SQL_BIGINT :
	            typeWidth = BIGINT_WIDTH;
	            break;
           case Types.SQL_INTEGER :
                 typeWidth = INTEGER_WIDTH;
                 break;
		   case Types.SQL_NUMERIC:
		   case Types.SQL_DECIMAL:
				typeWidth = DECIMAL_WIDTH;
				break;
		   default:
			   throw new RuntimeException("ss");
	    }
	}

}
