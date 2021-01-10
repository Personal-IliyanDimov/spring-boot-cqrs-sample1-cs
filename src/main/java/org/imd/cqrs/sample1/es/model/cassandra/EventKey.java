package org.imd.cqrs.sample1.es.model.cassandra;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
public class EventKey {

    @PrimaryKeyColumn(name = "pkey_event_part", type = PARTITIONED)
    private String eventPartition;

    @PrimaryKeyColumn(name = "pkey_aggr_type", ordinal = 0, type = CLUSTERED)
    private String aggregateType;

    @PrimaryKeyColumn(name = "pkey_aggr_aid", ordinal = 1, type = CLUSTERED)
    private String aggregateId;

    @PrimaryKeyColumn(name = "pkey_eid", ordinal = 2, type = CLUSTERED)
    private Long eventId;

}
