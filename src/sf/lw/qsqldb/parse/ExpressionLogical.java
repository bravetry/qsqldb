package sf.lw.qsqldb.parse;

import org.hsqldb.OpTypes;
import org.hsqldb.Session;


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
	
    public boolean testCondition(Session session) {
        return Boolean.TRUE.equals(getValue(session));
    }
}
