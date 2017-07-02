package domain;

import utility.Parameter;

import java.util.Objects;

import static utility.Validator.validLatitude;

public class Latitude extends DomainObject {

    private final double value;

    public Latitude(double latitude){
        this.value = latitude;
        validate();
    }

    public double getValue() {
        return value;
    }

    final void validate() {
        validLatitude(value, Parameter.LATITUDE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Latitude latitude = (Latitude) o;
        return Objects.equals(value, latitude.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
