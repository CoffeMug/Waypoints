package domain;

import utility.Parameter;
import java.util.Objects;

import static utility.Validator.validLongitude;

public class Longitude extends DomainObject {

    private final double value;

    public Longitude(double latitude){
        this.value = latitude;
        validate();
    }

    public double getValue() {
        return value;
    }

    final void validate() {
        validLongitude(value, Parameter.LONGITUDE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Longitude longitude = (Longitude) o;
        return Objects.equals(value, longitude.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
