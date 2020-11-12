package org.imd.cqrs.sample1.cs.es.events.user;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.cs.es.events.Event;

@Getter
@Setter
public class UserCreatedEvent extends Event {

    private Long userId;
    private String firstName;
    private String lastName;

}
