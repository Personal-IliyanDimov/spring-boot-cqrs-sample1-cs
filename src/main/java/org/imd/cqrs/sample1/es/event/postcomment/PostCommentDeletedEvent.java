package org.imd.cqrs.sample1.es.event.postcomment;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.es.event.Event;

import java.util.UUID;

@Getter
@Setter
public class PostCommentDeletedEvent extends Event {
    private UUID id;
}
