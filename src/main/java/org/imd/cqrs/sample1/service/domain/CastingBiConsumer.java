package org.imd.cqrs.sample1.service.domain;

public interface CastingBiConsumer<U> {
    public boolean supports(Object o);
    public void accept(Object o, U u);
}
