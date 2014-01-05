package sf.lw.qsqldb.parse;

public class ExpressionValue extends Expression{
    
	public ExpressionValue(int opType) {
		super(opType);
		
	}
	public ExpressionValue(Object value,Type datatype) {
		super(OpTypes.VALUE);
		this.dataType=datatype;
		this.valueData=value;
	}
}
