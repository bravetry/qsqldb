package sf.lw.qsqldb.parse;

public class Test {
    public boolean b;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test t=new Test();
//     System.out.println(Integer.MAX_VALUE);
//     System.out.println(Integer.MIN_VALUE);
//     System.out.println(Character.isLetter('s'));
     
		String sql="'222  \r\n  ssss" +
				"'ddd";
		System.out.println(sql);
		
		System.out.println(sql.indexOf("'",2));
				
     
	}

}
