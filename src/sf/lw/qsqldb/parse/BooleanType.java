package sf.lw.qsqldb.parse;

public class BooleanType extends Type {
	static final BooleanType booleanType = new BooleanType();
	
	private BooleanType() {
		super(Types.SQL_BOOLEAN, Types.SQL_BOOLEAN, 0, 0);
		
	}
	
    public static BooleanType getBooleanType() {
        return booleanType;
    }

}
