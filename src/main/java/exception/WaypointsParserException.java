package exception;

/**
 * Created by amin on 2017-06-06.
 */
public class WaypointsParserException extends RuntimeException {

    public WaypointsParserException(String message, Object... msgParams) {
        super(String.format(message, msgParams));
    }

}
