package org.imd.cqrs.sample1.es.event.post;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.es.event.Event;

@Getter
@Setter
public class PostCreatedEvent extends Event {

    private Long id;
    private String title;
}
