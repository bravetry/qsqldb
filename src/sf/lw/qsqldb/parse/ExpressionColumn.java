package sf.lw.qsqldb.parse;


public class ExpressionColumn extends Expression {
	boolean isParam;
	public ExpressionColumn(int type) {
		super(type);
        if (type == OpTypes.DYNAMIC_PARAM) {//是动态参数
            isParam = true;
        }
	}

}
