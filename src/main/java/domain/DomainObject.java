package domain;

abstract class DomainObject {

    abstract void validate();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();

}
