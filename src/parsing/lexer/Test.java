package parsing.lexer;

public class Test {
	public static void main(String[] args) {
		ListLexer lexer = new ListLexer(args[0]);
		Token t = lexer.nextToken();
		while (t.type != Token.EOF_TYPE) {
			System.out.println(t);
			t = lexer.nextToken();
		}
		System.out.println(t);// EOF
	}
}

/* 
 * input: java Test '[a, b ]'
 * 
 * output: <'[',LBACK> 
 * <'a',NAME> 
 * <',',COMMA> 
 * <'b',NAME> 
 * <']',RBACK>
 * <'<EOF>',<EOF>>
 */