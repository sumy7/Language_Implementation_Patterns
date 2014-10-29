package parsing.recursive_descent;

import parsing.lexer.Lexer;
import parsing.lexer.Token;

public class Parser {
	Lexer input; // ����Ĵʷ���Ԫ
	Token lookahead; // ��ǰ����ǰ������

	public Parser(Lexer input) {
		this.input = input;
		lookahead = input.nextToken();
	}

	/**
	 * �����ǰ���ʷ�������ƥ��x����ô�ͺ��Բ����أ����򱨴�
	 * 
	 * @param x
	 *            ��Ҫƥ��Ĵʷ�����
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
