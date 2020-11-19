package org.imd.cqrs.sample1.es.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TransactionEntity {
    private Long id;
    private LocalDateTime createTime;

    List<EventEntity> events;
}
