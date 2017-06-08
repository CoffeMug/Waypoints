package utility;

import exception.ValidationFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by amin on 2017-05-31.
 */
public final class Validator {

    private static final double MAX_LATITUDE = 90;
    private static final double MIN_LATITUDE = -90;
    private static final double MAX_LONGITUDE = 180;
    private static final double MIN_LONGITUDE = -180;

    private static final Logger LOGGER = LoggerFactory.getLogger(Validator.class);

    private Validator() {
        // This is a utility class
        throw new AssertionError();
    }

    public static void validLatitude(double value, String field) {
        if (value < MIN_LATITUDE || value > MAX_LATITUDE) {
            LOGGER.warn("Invalid json data for the attribute {} ", field);
            throw new ValidationFailedException("Field %s is out of range.", field);
        }
    }

    public static void validLongitude(double value, String field) {
        if (value < MIN_LONGITUDE || value > MAX_LONGITUDE) {
            LOGGER.warn("Invalid json data for the attribute {} ", field);
            throw new ValidationFailedException("Field %s is out of range.", field);
        }
    }

    public static void validSpeed(double value, String field) {
        if (value < 0) {
            LOGGER.warn("Invalid json data for the attribute {} ", field);
            throw new ValidationFailedException("Field %s is not a valid speed.", field);
        }
    }

}
