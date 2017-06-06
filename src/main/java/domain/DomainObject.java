package domain;

/**
 * Created by amin on 2017-05-31.
 */
abstract class DomainObject {

    abstract void validate();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

}
