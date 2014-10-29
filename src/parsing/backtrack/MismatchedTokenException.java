package parsing.backtrack;

public class MismatchedTokenException extends RecognitionException {

	private static final long serialVersionUID = 7817191432293370895L;

	public MismatchedTokenException() {
		super();
	}

	public MismatchedTokenException(String msg) {
		super(msg);
	}
}
