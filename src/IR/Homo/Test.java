package IR.Homo;

import parsing.lexer.Token;

public class Test {
    public static void main(String[] arg) {
        Token plus = new Token(Token.PLUS, "+");
        Token one = new Token(Token.INT, "1");
        Token two = new Token(Token.INT, "2");

        AST root = new AST(plus);
        root.addChild(new AST(one));
        root.addChild(new AST(two));

        System.out.println("1+2 tree: " + root.toStringTree());

        AST list = new AST(); // 空节点是某个列表的根节点
        list.addChild(new AST(one));
        list.addChild(new AST(two));
        System.out.println("1 and 2 in list: " + list.toStringTree());

    }
}
