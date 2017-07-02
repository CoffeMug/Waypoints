package utility;

import exception.ValidationFailedException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Validator {

    private static final double MAX_LATITUDE = 90;
    private static final double MIN_LATITUDE = -90;
    private static final double MAX_LONGITUDE = 180;
    private static final double MIN_LONGITUDE = -180;

    public static void validLatitude(double value, String field) {
        if (value < MIN_LATITUDE || value > MAX_LATITUDE) {
            log.warn("Invalid json data for the attribute {} ", field);
            throw new ValidationFailedException("Field %s is out of range.", field);
        }
    }

    public static void validLongitude(double value, String field) {
        if (value < MIN_LONGITUDE || value > MAX_LONGITUDE) {
            log.warn("Invalid json data for the attribute {} ", field);
            throw new ValidationFailedException("Field %s is out of range.", field);
        }
    }

    public static void validSpeed(double value, String field) {
        if (value < 0) {
            log.warn("Invalid json data for the attribute {} ", field);
            throw new ValidationFailedException("Field %s is not a valid speed.", field);
        }
    }

}
