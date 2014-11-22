package parsing.lexer;

public abstract class Lexer {
    public static final int INVALID_TOKEN_TYPE = 0;
    public static final char EOF = (char) -1;// EOF字符，即文件结尾

    String input; // 输入字符串
    int p = 0;// 当前输入字符的下标
    char c; // 当前字符

    public Lexer(String input) {
        this.input = input;
        c = input.charAt(p); // 预备向前看字符
    }

    /**
     * 向前移动一个字符；检测输入是否结束
     */
    public void consume() {
        p++;
        if (p >= input.length())
            c = EOF;
        else
            c = input.charAt(p);
    }

    /**
     * 确保x是输入流中的下一个字符
     */
    public void match(char x) {
        if (c == x)
            consume();
        else
            throw new Error("expecting " + x + "; found " + c);
    }

    public abstract Token nextToken();
}
