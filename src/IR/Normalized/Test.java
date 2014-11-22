package IR.Normalized;

import parsing.lexer.Token;

public class Test {
    public static void main(String[] args) {
        Token plus = new Token(Token.PLUS, "+");
        Token one = new Token(Token.INT, "1");
        Token two = new Token(Token.INT, "2");
        ExprNode root = new AddNode(new IntNode(one), plus, new IntNode(two));
        System.out.println(root.toStringTree());
    }

}
