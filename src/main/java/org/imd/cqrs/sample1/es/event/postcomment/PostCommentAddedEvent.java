package org.imd.cqrs.sample1.es.event.postcomment;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.es.event.Event;

import java.util.UUID;

@Getter
@Setter
public class PostCommentAddedEvent extends Event {
    private UUID id;
    private String review;
}
