package org.imd.cqrs.sample1.cs.eventproducer;

public interface AggregateEventProducer<E> {
    void sendEvent(E e);
}
