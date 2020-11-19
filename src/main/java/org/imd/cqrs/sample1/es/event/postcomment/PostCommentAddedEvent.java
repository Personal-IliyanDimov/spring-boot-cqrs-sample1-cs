package org.imd.cqrs.sample1.es.event.postcomment;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.es.event.Event;

@Getter
@Setter
public class PostCommentAddedEvent extends Event {
    private Long id;
    private String review;
}
