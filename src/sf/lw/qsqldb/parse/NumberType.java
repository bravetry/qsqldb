package sf.lw.qsqldb.parse;

import java.math.BigDecimal;




public class NumberType extends Type {
	static final int   integerPrecision             = 10;//java int
	static final int   bigintPrecision              = 19;//java long
	public static final int defaultNumericScale = 32;
	public static final int defaultNumericPrecision = 128;
	public static final int DECIMAL_WIDTH = 256;
	
	public static final int INTEGER_WIDTH  = 32;
	public static final int BIGINT_WIDTH   = 64;
	
    public static final Type SQL_NUMERIC_DEFAULT_INT =
        new NumberType(Types.NUMERIC, defaultNumericPrecision, 0);
    
    
	final int typeWidth;
	
	public NumberType(int type, long precision, int scale) {
		super(Types.SQL_NUMERIC, type, precision, scale);
		System.out.println(type);
		switch (type) {
	        case Types.SQL_BIGINT :
	            typeWidth = BIGINT_WIDTH;
	            break;
           case Types.SQL_INTEGER :
                 typeWidth = INTEGER_WIDTH;
                 break;
		   case Types.SQL_NUMERIC:
		   case Types.SQL_DECIMAL:
				typeWidth = DECIMAL_WIDTH;
				break;
		   default:
			   throw new RuntimeException("ss");
	    }
	}

	@Override
	public int compare(Session session, Object a, Object b) {
        if (a == b) {
            return 0;
        }
        //返回值含义,相等为0,左边大于右边是正1,右边大于左边是负-1
        //对于逻辑表达式里的相等判断,走不到这个方法
        //在什么地方会用到下面两个判断的返回值目前不清楚
        if (a == null) {
            return -1;
        }

        if (b == null) {
            return 1;
        }
        
        switch (typeCode) {
        case Types.SQL_INTEGER : {
        	if (b instanceof Integer) {
                int ai = ((Number) a).intValue();
                int bi = ((Number) b).intValue();

                return (ai > bi) ? 1
                                 : (bi > ai ? -1
                                            : 0);
            } else if (b instanceof Double) {
                double ai = ((Number) a).doubleValue();
                double bi = ((Number) b).doubleValue();

                return (ai > bi) ? 1
                                 : (bi > ai ? -1
                                            : 0);
            } else if (b instanceof BigDecimal) {
                BigDecimal ad = convertToDecimal(a);
                return ad.compareTo((BigDecimal) b);
            }
        
        }
        case Types.SQL_NUMERIC :
        case Types.SQL_DECIMAL : {
            BigDecimal bd = convertToDecimal(b);
            return ((BigDecimal) a).compareTo(bd);
        }
        default :
        	throw new RuntimeException("初始化数字失败NumberType:非法数据类型");
		
	    }
	}
        private static BigDecimal convertToDecimal(Object a) {

            if (a instanceof BigDecimal) {
                return (BigDecimal) a;
            } else if (a instanceof Integer || a instanceof Long) {
                return BigDecimal.valueOf(((Number) a).longValue());
            } else if (a instanceof Double) {
                double value = ((Number) a).doubleValue();

                if (Double.isInfinite(value) || Double.isNaN(value)) {
                    throw new RuntimeException("初始化数字失败");
                }

                return BigDecimal.valueOf(value);
            } else {
                throw new RuntimeException("初始化数字失败NumberType:非法数据类型");
            }
        }
}
