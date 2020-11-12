package org.imd.cqrs.sample1.cs.es.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserCreatedEvent extends Event {

    private Long userId;
    private String firstName;
    private String lastName;

}
