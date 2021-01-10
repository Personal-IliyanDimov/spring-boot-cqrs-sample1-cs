package org.imd.cqrs.sample1.es.model.cassandra;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;



@Getter
@Setter
@PrimaryKeyClass
public class AggregateKey implements Serializable {

    @PrimaryKeyColumn(name = "pkey_aggr_part", type = PARTITIONED)
    private String aggregatePartition;

    @PrimaryKeyColumn(name = "pkey_aggr_type", ordinal = 0, type = CLUSTERED)
    private String aggregateType;

    @PrimaryKeyColumn(name = "pkey_aggr_id", ordinal = 1, type = CLUSTERED)
    private String aggregateId;



}
