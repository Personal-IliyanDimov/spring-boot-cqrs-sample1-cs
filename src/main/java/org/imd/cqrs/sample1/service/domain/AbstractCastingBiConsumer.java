package org.imd.cqrs.sample1.service.domain;

public abstract class AbstractCastingBiConsumer<T, U> implements CastingBiConsumer<U> {

    private Class<T> tClass;

    public AbstractCastingBiConsumer(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public boolean supports(Object o) {
        return tClass.isInstance(o);
    }

    @Override
    public void accept(Object o, U u) {
        T t = tClass.cast(o);
        doAccept(t, u);
    }

    protected abstract void doAccept(T t, U u);
}
