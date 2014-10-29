package parsing.multi;

import parsing.lexer.Lexer;
import parsing.lexer.Token;

public class Parser {
	Lexer input; // ����Ĵʷ���Ԫ
	Token[] lookahead; // ���λ�����
	int k; // ��ǰ�����ŵĸ���
	int p = 0; // ���λ�������װ����һ���ʷ���Ԫ��λ��

	public Parser(Lexer input, int k) {
		this.input = input;
		this.k = k;
		lookahead = new Token[k]; // ������ǰ��������
		for (int i = 1; i <= k; i++) {
			consume(); // ��k����ǰ�����ų�ʼ��������
		}
	}

	public Token LT(int i) {
		return lookahead[(p + i - 1) % k]; //��ʽȡֵ
	}

	public int LA(int i) {
		return LT(i).type;
	}

	public void match(int x) {
		if (LA(1) == x)
			consume();
		else
			throw new Error("expecting " + Token.getTokenName(x) + "; found "
					+ LT(1));
	}

	public void consume() {
		lookahead[p] = input.nextToken(); // ����һ��λ���Ϸ���ʷ���Ԫ
		p = (p + 1) % k; // �����±�
	}
}
