package sf.lw.qsqldb.parse;

import java.util.HashMap;
import java.util.HashSet;





public class Tokens {
	public static final int OR                               = 197;
	public static final int NOT                              = 183;
	public static final int AND                              = 5;
	public static final int NULL                             = 186;
    public static final int FALSE                            = 106;
    public static final int TRUE                             = 294;
    public static final int X_UNKNOWN_TOKEN = -1;
    public static final int X_IDENTIFIER               = 870;
	public static final int X_VALUE                    = 869;
	public static final int X_MALFORMED_NUMERIC        = 878;
	public static final int X_ENDPARSE                 = 872;//扫描到结尾
	public static final int X_DELIMITED_IDENTIFIER     = 871;
	
	public static final int EQUALS                      = 396;
	static final String        T_QUESTION       = "?";
	static final int        QUESTION         = 818;
	
	public static final int IS                               = 142;
	
	public static final int UNKNOWN                          = 300;
	
	
	public static final int    MINUS                   = 814;
	static final int        OPENBRACKET      = 816;
	
	static final String        T_MINUS                 = "-";     
	static final String          T_EQUALS         = "=";
	public static final String  T_IS                = "IS";
    public static final int X_MALFORMED_STRING         = 877;
    public static final int X_MALFORMED_IDENTIFIER     = 883;
	
    public static int getKeywordID(String token, int defaultValue) {
    	if (reservedKeys.get(token)==null){
    		return defaultValue;
    	}
        return (Integer)reservedKeys.get(token);
    }
    
    public static int getNonKeywordID(String token, int defaultValue) {
    	if (commandSet.get(token)==null){
    		return defaultValue;
    	}
    	
        return (Integer)commandSet.get(token);
    }
    
    private static final HashMap commandSet = new HashMap(299);
    static {
    	
    }
    
    
    private static final HashMap reservedKeys =
        new HashMap(351);
    
    public static boolean isCoreKeyword(int token) {
        return coreReservedWords.contains(token);
    }
    private static final HashSet coreReservedWords;

    static {

        // minimal set of identifier not allowed as table / column / alias names
        // these are in effect interpreted as reserved words used by HSQLDB
        coreReservedWords = new HashSet(128);

        short[] keyword = {AND,OR};
    }
    
    static final String        T_AND              = "AND";
    static final String        T_OR                = "OR";
    static {
    	  reservedKeys.put(Tokens.T_AND, AND);
    	  reservedKeys.put(Tokens.T_OR, OR);
    	
//        reservedKeys.put(Tokens.T_ABS, ABS);
//        reservedKeys.put(Tokens.T_AGGREGATE, AGGREGATE);
//        reservedKeys.put(Tokens.T_ALL, ALL);
//        reservedKeys.put(Tokens.T_ALLOCATE, ALLOCATE);
//        reservedKeys.put(Tokens.T_ALTER, ALTER);
//        reservedKeys.put(Tokens.T_AND, AND);
//        reservedKeys.put(Tokens.T_ANY, ANY);
//        reservedKeys.put(Tokens.T_ARE, ARE);
//        reservedKeys.put(Tokens.T_ARRAY, ARRAY);
//        reservedKeys.put(Tokens.T_ARRAY_AGG, ARRAY_AGG);
//        reservedKeys.put(Tokens.T_AS, AS);
//        reservedKeys.put(Tokens.T_ASENSITIVE, ASENSITIVE);
//        reservedKeys.put(Tokens.T_ASYMMETRIC, ASYMMETRIC);
//        reservedKeys.put(Tokens.T_AT, AT);
//        reservedKeys.put(Tokens.T_ATOMIC, ATOMIC);
//        reservedKeys.put(Tokens.T_AUTHORIZATION, AUTHORIZATION);
//        reservedKeys.put(Tokens.T_AVG, AVG);
//        reservedKeys.put(Tokens.T_BEGIN, BEGIN);
//        reservedKeys.put(Tokens.T_BETWEEN, BETWEEN);
//        reservedKeys.put(Tokens.T_BIGINT, BIGINT);
//        reservedKeys.put(Tokens.T_BINARY, BINARY);
//        reservedKeys.put(Tokens.T_BIT_LENGTH, BIT_LENGTH);
//        reservedKeys.put(Tokens.T_BLOB, BLOB);
//        reservedKeys.put(Tokens.T_BOOLEAN, BOOLEAN);
//        reservedKeys.put(Tokens.T_BOTH, BOTH);
//        reservedKeys.put(Tokens.T_BY, BY);
//        reservedKeys.put(Tokens.T_CALL, CALL);
//        reservedKeys.put(Tokens.T_CALLED, CALLED);
//        reservedKeys.put(Tokens.T_CARDINALITY, CARDINALITY);
//        reservedKeys.put(Tokens.T_CASCADED, CASCADED);
//        reservedKeys.put(Tokens.T_CASE, CASE);
//        reservedKeys.put(Tokens.T_CAST, CAST);
//        reservedKeys.put(Tokens.T_CEIL, CEIL);
//        reservedKeys.put(Tokens.T_CEILING, CEILING);
//        reservedKeys.put(Tokens.T_CHAR, CHAR);
//        reservedKeys.put(Tokens.T_CHAR_LENGTH, CHAR_LENGTH);
//        reservedKeys.put(Tokens.T_CHARACTER, CHARACTER);
//        reservedKeys.put(Tokens.T_CHARACTER_LENGTH, CHARACTER_LENGTH);
//        reservedKeys.put(Tokens.T_CHECK, CHECK);
//        reservedKeys.put(Tokens.T_CLOB, CLOB);
//        reservedKeys.put(Tokens.T_CLOSE, CLOSE);
//        reservedKeys.put(Tokens.T_COALESCE, COALESCE);
//        reservedKeys.put(Tokens.T_COLLATE, COLLATE);
//        reservedKeys.put(Tokens.T_COLLECT, COLLECT);
//        reservedKeys.put(Tokens.T_COLUMN, COLUMN);
//        reservedKeys.put(Tokens.T_COMMIT, COMMIT);
//        reservedKeys.put(Tokens.T_COMPARABLE, COMPARABLE);
//        reservedKeys.put(Tokens.T_CONDITION, CONDITION);
//        reservedKeys.put(Tokens.T_CONNECT, CONNECT);
//        reservedKeys.put(Tokens.T_CONSTRAINT, CONSTRAINT);
//        reservedKeys.put(Tokens.T_CONVERT, CONVERT);
//        reservedKeys.put(Tokens.T_CORR, CORR);
//        reservedKeys.put(Tokens.T_CORRESPONDING, CORRESPONDING);
//        reservedKeys.put(Tokens.T_COUNT, COUNT);
//        reservedKeys.put(Tokens.T_COVAR_POP, COVAR_POP);
//        reservedKeys.put(Tokens.T_COVAR_SAMP, COVAR_SAMP);
//        reservedKeys.put(Tokens.T_CREATE, CREATE);
//        reservedKeys.put(Tokens.T_CROSS, CROSS);
//        reservedKeys.put(Tokens.T_CUBE, CUBE);
//        reservedKeys.put(Tokens.T_CUME_DIST, CUME_DIST);
//        reservedKeys.put(Tokens.T_CURRENT, CURRENT);
//        reservedKeys.put(Tokens.T_CURRENT_CATALOG, CURRENT_CATALOG);
//        reservedKeys.put(Tokens.T_CURRENT_DATE, CURRENT_DATE);
//        reservedKeys.put(Tokens.T_CURRENT_DEFAULT_TRANSFORM_GROUP,
//                         CURRENT_DEFAULT_TRANSFORM_GROUP);
//        reservedKeys.put(Tokens.T_CURRENT_PATH, CURRENT_PATH);
//        reservedKeys.put(Tokens.T_CURRENT_ROLE, CURRENT_ROLE);
//        reservedKeys.put(Tokens.T_CURRENT_SCHEMA, CURRENT_SCHEMA);
//        reservedKeys.put(Tokens.T_CURRENT_TIME, CURRENT_TIME);
//        reservedKeys.put(Tokens.T_CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
//        reservedKeys.put(Tokens.T_DO, DO);
//        reservedKeys.put(Tokens.T_CURRENT_TRANSFORM_GROUP_FOR_TYPE,
//                         CURRENT_TRANSFORM_GROUP_FOR_TYPE);
//        reservedKeys.put(Tokens.T_CURRENT_USER, CURRENT_USER);
//        reservedKeys.put(Tokens.T_CURSOR, CURSOR);
//        reservedKeys.put(Tokens.T_CYCLE, CYCLE);
//        reservedKeys.put(Tokens.T_DATE, DATE);
//        reservedKeys.put(Tokens.T_DAY, DAY);
//        reservedKeys.put(Tokens.T_DEALLOCATE, DEALLOCATE);
//        reservedKeys.put(Tokens.T_DEC, DEC);
//        reservedKeys.put(Tokens.T_DECIMAL, DECIMAL);
//        reservedKeys.put(Tokens.T_DECLARE, DECLARE);
//        reservedKeys.put(Tokens.T_DEFAULT, DEFAULT);
//        reservedKeys.put(Tokens.T_DELETE, DELETE);
//        reservedKeys.put(Tokens.T_DENSE_RANK, DENSE_RANK);
//        reservedKeys.put(Tokens.T_DEREF, DEREF);
//        reservedKeys.put(Tokens.T_DESCRIBE, DESCRIBE);
//        reservedKeys.put(Tokens.T_DETERMINISTIC, DETERMINISTIC);
//        reservedKeys.put(Tokens.T_DISCONNECT, DISCONNECT);
//        reservedKeys.put(Tokens.T_DISTINCT, DISTINCT);
//        reservedKeys.put(Tokens.T_DOUBLE, DOUBLE);
//        reservedKeys.put(Tokens.T_DROP, DROP);
//        reservedKeys.put(Tokens.T_DYNAMIC, DYNAMIC);
//        reservedKeys.put(Tokens.T_EACH, EACH);
//        reservedKeys.put(Tokens.T_ELEMENT, ELEMENT);
//        reservedKeys.put(Tokens.T_ELSE, ELSE);
//        reservedKeys.put(Tokens.T_ELSEIF, ELSEIF);
//        reservedKeys.put(Tokens.T_END, END);
//        reservedKeys.put(Tokens.T_END_EXEC, END_EXEC);
//        reservedKeys.put(Tokens.T_ESCAPE, ESCAPE);
//        reservedKeys.put(Tokens.T_EVERY, EVERY);
//        reservedKeys.put(Tokens.T_EXCEPT, EXCEPT);
//        reservedKeys.put(Tokens.T_EXEC, EXEC);
//        reservedKeys.put(Tokens.T_EXECUTE, EXECUTE);
//        reservedKeys.put(Tokens.T_EXISTS, EXISTS);
//        reservedKeys.put(Tokens.T_EXIT, EXIT);
//        reservedKeys.put(Tokens.T_EXP, EXP);
//        reservedKeys.put(Tokens.T_EXTERNAL, EXTERNAL);
//        reservedKeys.put(Tokens.T_EXTRACT, EXTRACT);
//        reservedKeys.put(Tokens.T_FALSE, FALSE);
//        reservedKeys.put(Tokens.T_FETCH, FETCH);
//        reservedKeys.put(Tokens.T_FILTER, FILTER);
//        reservedKeys.put(Tokens.T_FIRST_VALUE, FIRST_VALUE);
//        reservedKeys.put(Tokens.T_FLOAT, FLOAT);
//        reservedKeys.put(Tokens.T_FLOOR, FLOOR);
//        reservedKeys.put(Tokens.T_FOR, FOR);
//        reservedKeys.put(Tokens.T_FOREIGN, FOREIGN);
//        reservedKeys.put(Tokens.T_FREE, FREE);
//        reservedKeys.put(Tokens.T_FROM, FROM);
//        reservedKeys.put(Tokens.T_FULL, FULL);
//        reservedKeys.put(Tokens.T_FUNCTION, FUNCTION);
//        reservedKeys.put(Tokens.T_FUSION, FUSION);
//        reservedKeys.put(Tokens.T_GET, GET);
//        reservedKeys.put(Tokens.T_GLOBAL, GLOBAL);
//        reservedKeys.put(Tokens.T_GRANT, GRANT);
//        reservedKeys.put(Tokens.T_GROUP, GROUP);
//        reservedKeys.put(Tokens.T_GROUPING, GROUPING);
//        reservedKeys.put(Tokens.T_HANDLER, HANDLER);
//        reservedKeys.put(Tokens.T_HAVING, HAVING);
//        reservedKeys.put(Tokens.T_HOLD, HOLD);
//        reservedKeys.put(Tokens.T_HOUR, HOUR);
//        reservedKeys.put(Tokens.T_IDENTITY, IDENTITY);
//        reservedKeys.put(Tokens.T_IF, IF);
//        reservedKeys.put(Tokens.T_IMPORT, IMPORT);
//        reservedKeys.put(Tokens.T_IN, IN);
//        reservedKeys.put(Tokens.T_INDICATOR, INDICATOR);
//        reservedKeys.put(Tokens.T_INNER, INNER);
//        reservedKeys.put(Tokens.T_INOUT, INOUT);
//        reservedKeys.put(Tokens.T_INSENSITIVE, INSENSITIVE);
//        reservedKeys.put(Tokens.T_INSERT, INSERT);
//        reservedKeys.put(Tokens.T_INT, INT);
//        reservedKeys.put(Tokens.T_INTEGER, INTEGER);
//        reservedKeys.put(Tokens.T_INTERSECT, INTERSECT);
//        reservedKeys.put(Tokens.T_INTERSECTION, INTERSECTION);
//        reservedKeys.put(Tokens.T_INTERVAL, INTERVAL);
//        reservedKeys.put(Tokens.T_INTO, INTO);
//        reservedKeys.put(Tokens.T_IS, IS);
//        reservedKeys.put(Tokens.T_ITERATE, ITERATE);
//        reservedKeys.put(Tokens.T_JOIN, JOIN);
//        reservedKeys.put(Tokens.T_LAG, LAG);
//        reservedKeys.put(Tokens.T_LANGUAGE, LANGUAGE);
//        reservedKeys.put(Tokens.T_LARGE, LARGE);
//        reservedKeys.put(Tokens.T_LAST_VALUE, LAST_VALUE);
//        reservedKeys.put(Tokens.T_LATERAL, LATERAL);
//        reservedKeys.put(Tokens.T_LEAD, LEAD);
//        reservedKeys.put(Tokens.T_LEADING, LEADING);
//        reservedKeys.put(Tokens.T_LEAVE, LEAVE);
//        reservedKeys.put(Tokens.T_LEFT, LEFT);
//        reservedKeys.put(Tokens.T_LIKE, LIKE);
//        reservedKeys.put(Tokens.T_LIKE_REGX, LIKE_REGEX);
//        reservedKeys.put(Tokens.T_LN, LN);
//        reservedKeys.put(Tokens.T_LOCAL, LOCAL);
//        reservedKeys.put(Tokens.T_LOCALTIME, LOCALTIME);
//        reservedKeys.put(Tokens.T_LOCALTIMESTAMP, LOCALTIMESTAMP);
//        reservedKeys.put(Tokens.T_LOOP, LOOP);
//        reservedKeys.put(Tokens.T_LOWER, LOWER);
//        reservedKeys.put(Tokens.T_MATCH, MATCH);
//        reservedKeys.put(Tokens.T_MAX, MAX);
//        reservedKeys.put(Tokens.T_MAX_CARDINALITY, MAX_CARDINALITY);
//        reservedKeys.put(Tokens.T_MEMBER, MEMBER);
//        reservedKeys.put(Tokens.T_MERGE, MERGE);
//        reservedKeys.put(Tokens.T_METHOD, METHOD);
//        reservedKeys.put(Tokens.T_MIN, MIN);
//        reservedKeys.put(Tokens.T_MINUTE, MINUTE);
//        reservedKeys.put(Tokens.T_MOD, MOD);
//        reservedKeys.put(Tokens.T_MODIFIES, MODIFIES);
//        reservedKeys.put(Tokens.T_MODULE, MODULE);
//        reservedKeys.put(Tokens.T_MONTH, MONTH);
//        reservedKeys.put(Tokens.T_MULTISET, MULTISET);
//        reservedKeys.put(Tokens.T_NATIONAL, NATIONAL);
//        reservedKeys.put(Tokens.T_NATURAL, NATURAL);
//        reservedKeys.put(Tokens.T_NCHAR, NCHAR);
//        reservedKeys.put(Tokens.T_NCLOB, NCLOB);
//        reservedKeys.put(Tokens.T_NEW, NEW);
//        reservedKeys.put(Tokens.T_NO, NO);
//        reservedKeys.put(Tokens.T_NONE, NONE);
//        reservedKeys.put(Tokens.T_NORMALIZE, NORMALIZE);
//        reservedKeys.put(Tokens.T_NOT, NOT);
//        reservedKeys.put(Tokens.T_NTH_VALUE, NTH_VALUE);
//        reservedKeys.put(Tokens.T_NTILE, NTILE);
//        reservedKeys.put(Tokens.T_NULL, NULL);
//        reservedKeys.put(Tokens.T_NULLIF, NULLIF);
//        reservedKeys.put(Tokens.T_NUMERIC, NUMERIC);
//        reservedKeys.put(Tokens.T_OCCURRENCES_REGEX, OCCURRENCES_REGEX);
//        reservedKeys.put(Tokens.T_OCTET_LENGTH, OCTET_LENGTH);
//        reservedKeys.put(Tokens.T_OF, OF);
//        reservedKeys.put(Tokens.T_OFFSET, OFFSET);
//        reservedKeys.put(Tokens.T_OLD, OLD);
//        reservedKeys.put(Tokens.T_ON, ON);
//        reservedKeys.put(Tokens.T_ONLY, ONLY);
//        reservedKeys.put(Tokens.T_OPEN, OPEN);
//        reservedKeys.put(Tokens.T_OR, OR);
//        reservedKeys.put(Tokens.T_ORDER, ORDER);
//        reservedKeys.put(Tokens.T_OUT, OUT);
//        reservedKeys.put(Tokens.T_OUTER, OUTER);
//        reservedKeys.put(Tokens.T_OVER, OVER);
//        reservedKeys.put(Tokens.T_OVERLAPS, OVERLAPS);
//        reservedKeys.put(Tokens.T_OVERLAY, OVERLAY);
//        reservedKeys.put(Tokens.T_PARAMETER, PARAMETER);
//        reservedKeys.put(Tokens.T_PARTITION, PARTITION);
//        reservedKeys.put(Tokens.T_PERCENT_RANK, PERCENT_RANK);
//        reservedKeys.put(Tokens.T_PERCENTILE_CONT, PERCENTILE_CONT);
//        reservedKeys.put(Tokens.T_PERCENTILE_DISC, PERCENTILE_DISC);
//        reservedKeys.put(Tokens.T_POSITION, POSITION);
//        reservedKeys.put(Tokens.T_POSITION_REGEX, POSITION_REGEX);
//        reservedKeys.put(Tokens.T_POWER, POWER);
//        reservedKeys.put(Tokens.T_PRECISION, PRECISION);
//        reservedKeys.put(Tokens.T_PREPARE, PREPARE);
//        reservedKeys.put(Tokens.T_PRIMARY, PRIMARY);
//        reservedKeys.put(Tokens.T_PROCEDURE, PROCEDURE);
//        reservedKeys.put(Tokens.T_RANGE, RANGE);
//        reservedKeys.put(Tokens.T_RANK, RANK);
//        reservedKeys.put(Tokens.T_READS, READS);
//        reservedKeys.put(Tokens.T_REAL, REAL);
//        reservedKeys.put(Tokens.T_RECURSIVE, RECURSIVE);
//        reservedKeys.put(Tokens.T_REF, REF);
//        reservedKeys.put(Tokens.T_REFERENCES, REFERENCES);
//        reservedKeys.put(Tokens.T_REFERENCING, REFERENCING);
//        reservedKeys.put(Tokens.T_REGR_AVGX, REGR_AVGX);
//        reservedKeys.put(Tokens.T_REGR_AVGY, REGR_AVGY);
//        reservedKeys.put(Tokens.T_REGR_COUNT, REGR_COUNT);
//        reservedKeys.put(Tokens.T_REGR_INTERCEPT, REGR_INTERCEPT);
//        reservedKeys.put(Tokens.T_REGR_R2, REGR_R2);
//        reservedKeys.put(Tokens.T_REGR_SLOPE, REGR_SLOPE);
//        reservedKeys.put(Tokens.T_REGR_SXX, REGR_SXX);
//        reservedKeys.put(Tokens.T_REGR_SXY, REGR_SXY);
//        reservedKeys.put(Tokens.T_REGR_SYY, REGR_SYY);
//        reservedKeys.put(Tokens.T_RELEASE, RELEASE);
//        reservedKeys.put(Tokens.T_REPEAT, REPEAT);
//        reservedKeys.put(Tokens.T_RESIGNAL, RESIGNAL);
//        reservedKeys.put(Tokens.T_RETURN, RETURN);
//        reservedKeys.put(Tokens.T_RETURNS, RETURNS);
//        reservedKeys.put(Tokens.T_REVOKE, REVOKE);
//        reservedKeys.put(Tokens.T_RIGHT, RIGHT);
//        reservedKeys.put(Tokens.T_ROLLBACK, ROLLBACK);
//        reservedKeys.put(Tokens.T_ROLLUP, ROLLUP);
//        reservedKeys.put(Tokens.T_ROW, ROW);
//        reservedKeys.put(Tokens.T_ROW_NUMBER, ROW_NUMBER);
//        reservedKeys.put(Tokens.T_ROWS, ROWS);
//        reservedKeys.put(Tokens.T_SAVEPOINT, SAVEPOINT);
//        reservedKeys.put(Tokens.T_SCOPE, SCOPE);
//        reservedKeys.put(Tokens.T_SCROLL, SCROLL);
//        reservedKeys.put(Tokens.T_SEARCH, SEARCH);
//        reservedKeys.put(Tokens.T_SECOND, SECOND);
//        reservedKeys.put(Tokens.T_SELECT, SELECT);
//        reservedKeys.put(Tokens.T_SENSITIVE, SENSITIVE);
//        reservedKeys.put(Tokens.T_SESSION_USER, SESSION_USER);
//        reservedKeys.put(Tokens.T_SET, SET);
//        reservedKeys.put(Tokens.T_SIGNAL, SIGNAL);
//        reservedKeys.put(Tokens.T_SIMILAR, SIMILAR);
//        reservedKeys.put(Tokens.T_SMALLINT, SMALLINT);
//        reservedKeys.put(Tokens.T_SOME, SOME);
//        reservedKeys.put(Tokens.T_SPECIFIC, SPECIFIC);
//        reservedKeys.put(Tokens.T_SPECIFICTYPE, SPECIFICTYPE);
//        reservedKeys.put(Tokens.T_SQL, SQL);
//        reservedKeys.put(Tokens.T_SQLEXCEPTION, SQLEXCEPTION);
//        reservedKeys.put(Tokens.T_SQLSTATE, SQLSTATE);
//        reservedKeys.put(Tokens.T_SQLWARNING, SQLWARNING);
//        reservedKeys.put(Tokens.T_SQRT, SQRT);
//        reservedKeys.put(Tokens.T_STACKED, STACKED);
//        reservedKeys.put(Tokens.T_START, START);
//        reservedKeys.put(Tokens.T_STATIC, STATIC);
//        reservedKeys.put(Tokens.T_STDDEV_POP, STDDEV_POP);
//        reservedKeys.put(Tokens.T_STDDEV_SAMP, STDDEV_SAMP);
//        reservedKeys.put(Tokens.T_SUBMULTISET, SUBMULTISET);
//        reservedKeys.put(Tokens.T_SUBSTRING, SUBSTRING);
//        reservedKeys.put(Tokens.T_SUBSTRING_REGEX, SUBSTRING_REGEX);
//        reservedKeys.put(Tokens.T_SUM, SUM);
//        reservedKeys.put(Tokens.T_SYMMETRIC, SYMMETRIC);
//        reservedKeys.put(Tokens.T_SYSTEM, SYSTEM);
//        reservedKeys.put(Tokens.T_SYSTEM_USER, SYSTEM_USER);
//        reservedKeys.put(Tokens.T_TABLE, TABLE);
//        reservedKeys.put(Tokens.T_TABLESAMPLE, TABLESAMPLE);
//        reservedKeys.put(Tokens.T_THEN, THEN);
//        reservedKeys.put(Tokens.T_TIME, TIME);
//        reservedKeys.put(Tokens.T_TIMESTAMP, TIMESTAMP);
//        reservedKeys.put(Tokens.T_TIMEZONE_HOUR, TIMEZONE_HOUR);
//        reservedKeys.put(Tokens.T_TIMEZONE_MINUTE, TIMEZONE_MINUTE);
//        reservedKeys.put(Tokens.T_TO, TO);
//        reservedKeys.put(Tokens.T_TRAILING, TRAILING);
//        reservedKeys.put(Tokens.T_TRANSLATE, TRANSLATE);
//        reservedKeys.put(Tokens.T_TRANSLATE_REGEX, TRANSLATE_REGEX);
//        reservedKeys.put(Tokens.T_TRANSLATION, TRANSLATION);
//        reservedKeys.put(Tokens.T_TREAT, TREAT);
//        reservedKeys.put(Tokens.T_TRIGGER, TRIGGER);
//        reservedKeys.put(Tokens.T_TRIM, TRIM);
//        reservedKeys.put(Tokens.T_TRIM_ARRAY, TRIM_ARRAY);
//        reservedKeys.put(Tokens.T_TRUE, TRUE);
//        reservedKeys.put(Tokens.T_TRUNCATE, TRUNCATE);
//        reservedKeys.put(Tokens.T_UESCAPE, UESCAPE);
//        reservedKeys.put(Tokens.T_UNDO, UNDO);
//        reservedKeys.put(Tokens.T_UNION, UNION);
//        reservedKeys.put(Tokens.T_UNIQUE, UNIQUE);
//        reservedKeys.put(Tokens.T_UNKNOWN, UNKNOWN);
//        reservedKeys.put(Tokens.T_UNNEST, UNNEST);
//        reservedKeys.put(Tokens.T_UNTIL, UNTIL);
//        reservedKeys.put(Tokens.T_UPDATE, UPDATE);
//        reservedKeys.put(Tokens.T_UPPER, UPPER);
//        reservedKeys.put(Tokens.T_USER, USER);
//        reservedKeys.put(Tokens.T_USING, USING);
//        reservedKeys.put(Tokens.T_VALUE, VALUE);
//        reservedKeys.put(Tokens.T_VALUES, VALUES);
//        reservedKeys.put(Tokens.T_VAR_POP, VAR_POP);
//        reservedKeys.put(Tokens.T_VAR_SAMP, VAR_SAMP);
//        reservedKeys.put(Tokens.T_VARBINARY, VARBINARY);
//        reservedKeys.put(Tokens.T_VARCHAR, VARCHAR);
//        reservedKeys.put(Tokens.T_VARYING, VARYING);
//        reservedKeys.put(Tokens.T_WHEN, WHEN);
//        reservedKeys.put(Tokens.T_WHENEVER, WHENEVER);
//        reservedKeys.put(Tokens.T_WHERE, WHERE);
//        reservedKeys.put(Tokens.T_WIDTH_BUCKET, WIDTH_BUCKET);
//        reservedKeys.put(Tokens.T_WINDOW, WINDOW);
//        reservedKeys.put(Tokens.T_WITH, WITH);
//        reservedKeys.put(Tokens.T_WITHIN, WITHIN);
//        reservedKeys.put(Tokens.T_WITHOUT, WITHOUT);
//        reservedKeys.put(Tokens.T_WHILE, WHILE);
//        reservedKeys.put(Tokens.T_YEAR, YEAR);
    }
    
}
