package parsing.recursive_descent;

import parsing.lexer.Lexer;
import parsing.lexer.Token;

public class ListParser extends Parser {
	public ListParser(Lexer input) {
		super(input);
	}

	/**
	 * list : '[' elements ']' ;
	 */
	public void list() {
		match(Token.LBRACK);
		elements();
		match(Token.RBRACK);
	}

	/**
	 * elements : element (',' element)* ;
	 */
	void elements() {
		element();
		while (lookahead.type == Token.COMMA) {
			match(Token.COMMA);
			element();
		}
	}

	/**
	 * element : NAME | list ;
	 */
	void element() {
		if (lookahead.type == Token.NAME)
			match(Token.NAME);
		else if (lookahead.type == Token.LBRACK)
			list();
		else
			throw new Error("expecting name or list; found " + lookahead);
	}
}
