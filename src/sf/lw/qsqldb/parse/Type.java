package sf.lw.qsqldb.parse;

public class Type {
	
	public final int        typeCode;
	Type(int typeGroup, int type, long precision, int scale) {
		this.typeCode=type;
	}
	
    public static final NumberType SQL_NUMERIC =
        new NumberType(Types.SQL_NUMERIC, NumberType.defaultNumericPrecision, 0);
    
    public static final NumberType SQL_INTEGER =
        new NumberType(Types.SQL_INTEGER, NumberType.integerPrecision, 0);
    
    public static final NumberType SQL_BIGINT =
        new NumberType(Types.SQL_BIGINT, NumberType.bigintPrecision, 0);
    
    public static final BooleanType SQL_BOOLEAN = BooleanType.getBooleanType();
    
    
}
