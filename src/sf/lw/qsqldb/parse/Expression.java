package sf.lw.qsqldb.parse;


public class Expression {
   int        opType;
   Object   valueData;
   Type      dataType;
   
   
   public static final int LEFT    = 0;//左边
   public static final int RIGHT   = 1;//右边
   public static final int UNARY   = 1;//一元组
   public static final int BINARY  = 2;//二元组
   public static final int TERNARY = 3;//三元组
   
   static final Expression EXPR_TRUE  = new ExpressionLogical(true);
   static final Expression EXPR_FALSE = new ExpressionLogical(false);
   
   
   protected Expression[] nodes;
   
   public Expression(int opType){
	  this.opType=opType;
   }
   public Object getValue(Session session) {
       switch (opType) {
         case OpTypes.VALUE :
              return valueData;
       }
	   return null;
   }
   
}
