package org.imd.cqrs.sample1.es.store;

public interface AggregateStore<T, K> {

    T findAggregate(K k);

    K storeAggregate(T t);

    void updateAggregate(T t);

    void deleteAggregate(K k);
}
