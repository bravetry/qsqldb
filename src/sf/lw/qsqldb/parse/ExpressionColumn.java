package sf.lw.qsqldb.parse;


public class ExpressionColumn extends Expression {
	boolean isParam;
	public ExpressionColumn(int type) {
		super(type);
        if (type == OpTypes.DYNAMIC_PARAM) {//是动态参数 ?
            isParam = true;
        }
	}
	
	@Override
	public Object getValue(Session session){
		switch(opType){
		    case OpTypes.DYNAMIC_PARAM : {
		    	 return session.sessionContext.dynamicArguments[parameterIndex];
		    }
		    default: throw new RuntimeException("目前不支持这种类型的列:"+opType); 	 
		}
	}
	
	

}
