package org.imd.cqrs.sample1.es.event.relation;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.es.event.Event;

import java.util.UUID;

@Getter
@Setter
public class PostToPostCommentRelationCreatedEvent extends Event {
    private UUID postId;
    private UUID postCommentId;
}
