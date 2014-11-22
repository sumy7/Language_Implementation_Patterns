package parsing.lexer;

public class Token {
    public static int EOF_TYPE = 1;// 表示EOF词法类型
    public static int NAME = 2;
    public static int COMMA = 3;
    public static int LBRACK = 4;
    public static int RBRACK = 5;
    public static int EQUALS = 6;
    public static int PLUS = 7;
    public static int MINUS = 8;
    public static int INT = 101;
    public static String[] tokenNames = { "n/a", "<EOF>", "NAME", "COMMA",
            "LBACK", "RBACK", "EQUALS", "PLUS", "MINUS" };

    public static String[] tokenName_Type = { "n/a", "INT" }; // 基本数据类型

    public static String getTokenName(int x) {
        if (x > 100) {
            return tokenName_Type[x - 100];
        }
        return tokenNames[x];
    }

    public int type;
    public String text;

    public Token(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public Token(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String tname = getTokenName(type);
        return "<'" + text + "'," + tname + ">";
    }

    /**
     * 只返回节点的文本
     */
    public String toText() {
        return text;
    }
}
