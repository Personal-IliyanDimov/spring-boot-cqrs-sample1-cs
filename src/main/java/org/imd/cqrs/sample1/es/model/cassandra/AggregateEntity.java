package org.imd.cqrs.sample1.es.model.cassandra;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table
public class AggregateEntity {

    @PrimaryKey
    private AggregateKey aggregateKey;

    @Column
    private String version;

    @Column
    private LocalDateTime lastUpdated;

    @Column
    private String eventsLocation;
}
