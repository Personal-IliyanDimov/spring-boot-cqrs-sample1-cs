package org.imd.cqrs.sample1.es.store.cassandra;

import org.imd.cqrs.sample1.es.store.AggregateStore;
import org.imd.cqrs.sample1.model.domain.aggregate.PostAggregate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Profile("")
@Component
public class CassandraPostAggregateStore implements AggregateStore<PostAggregate, UUID> {

    @Override
    public PostAggregate findAggregate(UUID uuid) {
        return null;
    }

    @Override
    public UUID storeAggregate(PostAggregate postAggregate) {
        return null;
    }
}
