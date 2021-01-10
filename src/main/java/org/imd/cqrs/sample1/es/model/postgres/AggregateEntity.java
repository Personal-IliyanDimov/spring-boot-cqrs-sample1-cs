package org.imd.cqrs.sample1.es.model.postgres;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.es.model.shared.AggregateType;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class AggregateEntity {

    private AggregateType type;
    private String aggregateId;

    private String version;
    private LocalDateTime lastUpdated;

    private List<EventEntity> events;
}
