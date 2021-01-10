package org.imd.cqrs.sample1.es.model.postgres;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.es.model.shared.AggregateType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.CLUSTERED;
import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;


@Getter
@Setter
@PrimaryKeyClass
public class AggregateKey implements Serializable {

    @PrimaryKeyColumn(name = "pkey_atype", type = PARTITIONED)
    private AggregateType type;

    @PrimaryKeyColumn(name = "pkey_aid", ordinal = 0, type = CLUSTERED)
    private String aggregateId;



}
