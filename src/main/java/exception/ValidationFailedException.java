package exception;

/**
 * Created by amin on 2017-05-31.
 */

public class ValidationFailedException extends RuntimeException {

    public ValidationFailedException(String message, Object... msgParams) {
        super(String.format(message, msgParams));
    }

}
