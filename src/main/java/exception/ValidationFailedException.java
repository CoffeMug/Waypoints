package exception;

public class ValidationFailedException extends RuntimeException {

    public ValidationFailedException(String message, Object... msgParams) {
        super(String.format(message, msgParams));
    }

}
