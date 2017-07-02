package domain;

import utility.Parameter;

import java.util.Objects;

import static utility.Validator.validSpeed;

public class Speed extends DomainObject{

    private final double value;

    public Speed(double speed){
        this.value = speed;
        validate();
    }

    public double getValue() {
        return value;
    }

    final void validate() {
        validSpeed(value, Parameter.SPEED);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speed speed = (Speed) o;
        return Objects.equals(value, speed.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
