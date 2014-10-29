package parsing.lexer;

public class Token {
	public static int EOF_TYPE = 1;// 表示EOF词法类型
	public static int NAME = 2;
	public static int COMMA = 3;
	public static int LBRACK = 4;
	public static int RBRACK = 5;
	public static int EQUALS = 6;
	public static String[] tokenNames = { "n/a", "<EOF>", "NAME", "COMMA",
			"LBACK", "RBACK", "EQUALS" };

	public static String getTokenName(int x) {
		return tokenNames[x];
	}

	public int type;
	public String text;

	public Token(int type, String text) {
		this.type = type;
		this.text = text;
	}

	@Override
	public String toString() {
		String tname = getTokenName(type);
		return "<'" + text + "'," + tname + ">";
	}
}
