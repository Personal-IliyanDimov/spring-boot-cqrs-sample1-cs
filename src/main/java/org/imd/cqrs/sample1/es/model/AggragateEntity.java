package org.imd.cqrs.sample1.es.model;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.es.model.enums.AggregateType;

import java.util.List;

@Getter
@Setter
public class AggragateEntity {

    private AggregateType type;
    private String aggregateId;

    private List<TransactionEntity> transactionEntities;
}
