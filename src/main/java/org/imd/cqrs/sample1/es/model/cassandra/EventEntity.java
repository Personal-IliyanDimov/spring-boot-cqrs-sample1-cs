package org.imd.cqrs.sample1.es.model.cassandra;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@Setter
@Table
public class EventEntity {
   @PrimaryKey
   private EventKey eventKey;

   private String type;
   private String data;
   private Integer sequence;
}
