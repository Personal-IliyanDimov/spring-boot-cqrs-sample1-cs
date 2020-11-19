package org.imd.cqrs.sample1.es.event.relation;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.es.event.Event;

@Getter
@Setter
public class PostToPostCommentRelationCreatedEvent extends Event {
    private Long postId;
    private Long postCommentId;
}
