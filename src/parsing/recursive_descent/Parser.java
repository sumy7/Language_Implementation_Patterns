package parsing.recursive_descent;

import parsing.lexer.Lexer;
import parsing.lexer.Token;

public class Parser {
    Lexer input; // 输入的词法单元
    Token lookahead; // 当前的向前看符号

    public Parser(Lexer input) {
        this.input = input;
        lookahead = input.nextToken();
    }

    /**
     * 如果向前看词法类型能匹配x，那么就忽略并返回；否则报错
     * 
     * @param x
     *            需要匹配的词法类型
     */
    public void match(int x) {
        if (lookahead.type == x)
            consume();
        else
            throw new Error("expecting " + Token.getTokenName(x) + "; found "
                    + lookahead);
    }

    public void consume() {
        lookahead = input.nextToken();
    }
}
