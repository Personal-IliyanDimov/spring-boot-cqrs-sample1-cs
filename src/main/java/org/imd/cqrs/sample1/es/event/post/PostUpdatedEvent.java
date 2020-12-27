package org.imd.cqrs.sample1.es.event.post;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.es.event.Event;

import java.util.UUID;

@Getter
@Setter
public class PostUpdatedEvent extends Event {

    private UUID id;
    private String title;

}
