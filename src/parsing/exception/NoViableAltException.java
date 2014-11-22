package parsing.exception;

public class NoViableAltException extends RecognitionException {

    private static final long serialVersionUID = -7904010744795487060L;

    public NoViableAltException(String msg) {
        super(msg);
    }

}
