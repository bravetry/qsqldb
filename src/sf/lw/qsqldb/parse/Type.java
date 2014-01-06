package sf.lw.qsqldb.parse;


public abstract class Type {
	
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
    
    public abstract int compare(Session session, Object a, Object b);

    //为什么比较的结果是用数字来表示的
    public int compare(Session session, Object a, Object b,int opType){
        if (a == b) {//指向同一个对象时才相等.注意,值相同的基本类型这里不一定相等
            return 0;
        }
        return compare(session,a,b);
    }
    
}
