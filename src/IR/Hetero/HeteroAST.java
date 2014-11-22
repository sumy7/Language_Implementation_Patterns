package IR.Hetero;

import parsing.lexer.Token;

/**
 * 不规则异性AST节点类型
 */
public abstract class HeteroAST {
    Token token;

    public HeteroAST() {
    }

    public HeteroAST(Token t) {
        this.token = t;
    }

    public HeteroAST(int tokenType) {
        this.token = new Token(tokenType);
    }

    /**
     * 返回节点的文本表示
     */
    public String toString() {
        return token.toText();
    }

    /**
     * 返回树的文本表示，默认返回节点的文本表示
     */
    public String toStringTree() {
        return toString();
    }
}
