package org.imd.cqrs.sample1.es.store.postgres;

import org.imd.cqrs.sample1.es.store.AggregateStore;
import org.imd.cqrs.sample1.model.domain.aggregate.PostAggregate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PostgresPostAggregateStore implements AggregateStore<PostAggregate, UUID> {

    @Override
    public PostAggregate findAggregate(UUID uuid) {
        return null;
    }

    @Override
    public UUID storeAggregate(PostAggregate postAggregate) {
        return null;
    }
}
