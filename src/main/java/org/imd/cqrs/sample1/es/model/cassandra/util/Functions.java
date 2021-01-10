package org.imd.cqrs.sample1.es.model.cassandra.util;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Functions {

    public String findAggregatePartition(final String aggregateType, final String aggregateId) {
        return aggregateType.toLowerCase(Locale.ROOT);
    }

    public String findEventPartition(final String aggregateType, final String aggregateId) {
        return aggregateType.toLowerCase(Locale.ROOT) + "-"+ aggregateId.toLowerCase(Locale.ROOT);
    }
}
