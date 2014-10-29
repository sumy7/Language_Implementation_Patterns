package parsing.multi;

import parsing.lexer.Lexer;
import parsing.lexer.Token;

public class Parser {
	Lexer input; // 输入的词法单元
	Token[] lookahead; // 环形缓冲区
	int k; // 向前看符号的个数
	int p = 0; // 环形缓冲区中装填下一个词法单元的位置

	public Parser(Lexer input, int k) {
		this.input = input;
		this.k = k;
		lookahead = new Token[k]; // 开辟向前看缓冲区
		for (int i = 1; i <= k; i++) {
			consume(); // 用k个向前看符号初始化缓冲区
		}
	}

	public Token LT(int i) {
		return lookahead[(p + i - 1) % k]; //环式取值
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
		lookahead[p] = input.nextToken(); // 在下一个位置上放入词法单元
		p = (p + 1) % k; // 自增下标
	}
}
