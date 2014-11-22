package parsing.lexer;

public class ListLexer extends Lexer {

    public ListLexer(String input) {
        super(input);
    }

    @Override
    public Token nextToken() {
        while (c != EOF) {
            switch (c) {
            case ' ':
            case '\t':
            case '\n':
            case '\r':
                WS();
                continue;
            case ',':
                consume();
                return new Token(Token.COMMA, ",");
            case '[':
                consume();
                return new Token(Token.LBRACK, "[");
            case ']':
                consume();
                return new Token(Token.RBRACK, "]");
            case '=':
                consume();
                return new Token(Token.EQUALS, "=");
            default:
                if (isLETTER())
                    return NAME();
                throw new Error("invalid character: " + c);
            }
        }
        return new Token(Token.EOF_TYPE, "<EOF>");
    }

    void WS() {
        while (c == ' ' || c == '\t' || c == '\n' || c == '\r')
            consume();
    }

    Token NAME() {
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(c);
            consume();
        } while (isLETTER());
        return new Token(Token.NAME, buf.toString());
    }

    boolean isLETTER() {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'A';
    }

}
