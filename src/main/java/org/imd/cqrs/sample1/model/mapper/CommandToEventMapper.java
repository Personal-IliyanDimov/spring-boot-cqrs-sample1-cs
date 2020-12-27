package org.imd.cqrs.sample1.model.mapper;

import org.imd.cqrs.sample1.cs.command.post.CreatePostCommand;
import org.imd.cqrs.sample1.cs.command.post.DeletePostCommand;
import org.imd.cqrs.sample1.cs.command.post.UpdatePostCommand;
import org.imd.cqrs.sample1.cs.command.postcomment.AddPostCommentCommand;
import org.imd.cqrs.sample1.cs.command.postcomment.DeletePostCommentCommand;
import org.imd.cqrs.sample1.cs.command.postcomment.UpdatePostCommentCommand;
import org.imd.cqrs.sample1.es.event.post.PostCreatedEvent;
import org.imd.cqrs.sample1.es.event.post.PostDeletedEvent;
import org.imd.cqrs.sample1.es.event.post.PostUpdatedEvent;
import org.imd.cqrs.sample1.es.event.postcomment.PostCommentAddedEvent;
import org.imd.cqrs.sample1.es.event.postcomment.PostCommentDeletedEvent;
import org.imd.cqrs.sample1.es.event.postcomment.PostCommentUpdatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommandToEventMapper {

    public static final CommandToEventMapper INSTANCE = Mappers.getMapper( CommandToEventMapper.class );

    public PostCreatedEvent toEvent(CreatePostCommand command);
    public PostDeletedEvent toEvent(DeletePostCommand command);
    public PostUpdatedEvent toEvent(UpdatePostCommand command);

    public PostCommentAddedEvent   toEvent(AddPostCommentCommand command);
    public PostCommentDeletedEvent toEvent(DeletePostCommentCommand command);
    public PostCommentUpdatedEvent toEvent(UpdatePostCommentCommand command);
}
