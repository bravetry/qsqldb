package sf.lw.qsqldb.parse;

public class SqlParserBase {
	Scanner scanner;
	Token token;

	public SqlParserBase(Scanner scanner) {
		this.scanner = scanner;
		this.token = this.scanner.token;
	}

	// 读一个token
	public void read() {
		scanner.scanNext();
		if (token.isMalformed) {
			// 错误信息
			throw new RuntimeException("sql解析错误");
		}

	}

}
