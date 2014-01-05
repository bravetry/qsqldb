package sf.lw.qsqldb.parse;

import org.hsqldb.OpTypes;
import org.hsqldb.Session;

public class ExpressionArithmetic extends Expression{

	public ExpressionArithmetic(int opType,Expression e) {
		super(opType);
	
        nodes       = new Expression[UNARY];
        nodes[LEFT] = e;

        switch (opType) {
            case OpTypes.NEGATE :
                return;
            default :
                throw new RuntimeException("表达式操作类型错误");
        }
	}
	
    ExpressionArithmetic(int type, Expression left, Expression right) {
        super(type);
        nodes        = new Expression[BINARY];
        nodes[LEFT]  = left;
        nodes[RIGHT] = right;
        switch (opType) {
            case OpTypes.ADD :
                return;
            default :
            	throw new RuntimeException("表达式操作类型错误");
        }
    }
    
    public Object getValue(Session session) {

        switch (opType) {
            case OpTypes.VALUE :
                return valueData;
        }
    }
    

}
