package org.imd.cqrs.sample1.es.model.postgres;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventEntity {

   private Long id;
   private String type;
   private String data;
   private Integer sequence;
}
