package sf.lw.qsqldb.parse;



public class ExpressionLogical extends Expression {

	public ExpressionLogical(int type, Expression left, Expression right) {
        super(type);
        
        nodes        = new Expression[BINARY];
        nodes[LEFT]  = left;
        nodes[RIGHT] = right;
        
        switch (opType) {
        case OpTypes.OR :
             dataType = Type.SQL_BOOLEAN;
             break;
        }
	}
	
	public ExpressionLogical(int type, Expression left){
		super(type);
		nodes    =new Expression[UNARY];
		nodes[LEFT]=left;
		switch(opType){
		   case  OpTypes.NOT:
		         dataType=Type.SQL_BOOLEAN;
		}
	}
	public ExpressionLogical(boolean b){
		super(OpTypes.VALUE);
		dataType=Type.SQL_BOOLEAN;
		valueData=b?Boolean.TRUE:Boolean.FALSE;
	}
	
	public Object getValue(Session session){
		switch(opType){

		   case OpTypes.EQUAL :
			     //TODO 多值
			     Object vl=nodes[LEFT].getValue(session);
			     Object vr=nodes[RIGHT].getValue(session);
			     return compareValues(session, vl, vr);
		   case OpTypes.AND:
			     Boolean b1=(Boolean) nodes[LEFT].getValue(session);
			     if(Boolean.FALSE.equals(b1)){
			    	 return Boolean.FALSE;
			     }
			     
			     Boolean b2=(Boolean) nodes[RIGHT].getValue(session);
			     if(Boolean.FALSE.equals(b2)){
			    	 return Boolean.FALSE;
			     }
			     
			     if (b1==null||b2==null){
			    	 return null;
			     }
			     
                 return Boolean.TRUE;
           default:throw new RuntimeException("非法表达式");
		}
	}
	
	public Boolean compareValues(Session session,Object left,Object right){
		int result=0;
		if(left==null||right==null){
			return null;
		}
		result=nodes[LEFT].dataType.compare(session, left, right,opType);
		switch(opType){
		    case OpTypes.EQUAL :
		         return result==0?Boolean.TRUE:Boolean.FALSE;
		    default: throw new RuntimeException("目前不支持该类型的比较"+opType); 
		}
	}
	
	
	
    public boolean testCondition(Session session) {
        return Boolean.TRUE.equals(getValue(session));
    }
}
