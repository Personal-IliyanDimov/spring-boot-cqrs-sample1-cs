package org.imd.cqrs.sample1.es.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventEntity {
   private Long id;
   private String data;
   private String type;

   private TransactionEntity transaction;
   private Integer sequenceInTransaction;
}
