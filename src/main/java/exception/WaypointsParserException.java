package exception;

public class WaypointsParserException extends RuntimeException {

    public WaypointsParserException(String message, Object... msgParams) {
        super(String.format(message, msgParams));
    }

}
