package parsing.lexer;

public abstract class Lexer {
	public static final int INVALID_TOKEN_TYPE = 0;
	public static final char EOF = (char) -1;// EOF�ַ������ļ���β

	String input; // �����ַ���
	int p = 0;// ��ǰ�����ַ����±�
	char c; // ��ǰ�ַ�

	public Lexer(String input) {
		this.input = input;
		c = input.charAt(p); // Ԥ����ǰ���ַ�
	}

	/**
	 * ��ǰ�ƶ�һ���ַ�����������Ƿ����
	 */
	public void consume() {
		p++;
		if (p >= input.length())
			c = EOF;
		else
			c = input.charAt(p);
	}

	/**
	 * ȷ��x���������е���һ���ַ�
	 */
	public void match(char x) {
		if (c == x)
			consume();
		else
			throw new Error("expecting " + x + "; found " + c);
	}

	public abstract Token nextToken();
}
