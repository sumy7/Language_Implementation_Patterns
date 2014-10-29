package parsing.backtrack;

import parsing.lexer.Lexer;
import parsing.lexer.Token;

public class BacktrackParser extends Parser {

	public BacktrackParser(Lexer input) {
		super(input);
	}

	/**
	 * stat : list EOF | assign EOF ;
	 */
	public void stat() throws RecognitionException {
		// ���Խ���ѡ�� 1�� list EOF
		if (speculate_stat_alt1()) {
			list();
			match(Token.EOF_TYPE);
			// ���Խ���ѡ��2�� assign EOF
		} else if (speculate_stat_alt2()) {
			assign();
			match(Token.EOF_TYPE);
			// ��������ƥ�䣬����
		} else
			throw new NoViableAltException("expection stat found " + LT(1));
	}

	public boolean speculate_stat_alt1() {
		boolean success = true;
		mark(); // ��ǵ�ǰλ��
		try {
			list();
			match(Token.EOF_TYPE);
		} catch (RecognitionException e) {
			success = false;
		}
		release(); // ����
		return success;
	}

	public boolean speculate_stat_alt2() {
		boolean success = true;
		mark(); // ��ǵ�ǰλ��
		try {
			assign();
			match(Token.EOF_TYPE);
		} catch (RecognitionException e) {
			success = false;
		}
		release(); // ����
		return success;
	}

	/**
	 * assign : list '=' list ;
	 */
	public void assign() throws RecognitionException {
		list();
		match(Token.EQUALS);
		list();
	}

	/**
	 * list : '[' elements ']' ;
	 */
	public void list() throws RecognitionException {
		match(Token.LBRACK);
		elements();
		match(Token.RBRACK);
	}

	/**
	 * elements : element (',' element)* ;
	 */
	void elements() throws RecognitionException {
		element();
		while (LA(1) == Token.COMMA) {
			match(Token.COMMA);
			element();
		}
	}

	/**
	 * element : NAME '=' NAME | NAME | list ;
	 */
	void element() throws RecognitionException {
		if (LA(1) == Token.NAME && LA(2) == Token.EQUALS) {
			match(Token.NAME);
			match(Token.EQUALS);
			match(Token.NAME);
		} else if (LA(1) == Token.NAME)
			match(Token.NAME);
		else if (LA(1) == Token.LBRACK)
			list();
		else
			throw new Error("expecting name or list; found " + LT(1));
	}
}
