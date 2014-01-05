package sf.lw.qsqldb.parse;

public interface OpTypes {
    int NONE                 = 0,
        IS_NULL              = 47,
        NOT                  = 48,    // logical operations
        AND                  = 49,
        OR                   = 50,
        EQUAL                = 40,
        
        NEGATE               = 31,    // arithmetic operations
        ADD                  = 32,
        
        VALUE                = 1,    //常量值
        DYNAMIC_PARAM        = 8;    //动态参数
    
     
}
