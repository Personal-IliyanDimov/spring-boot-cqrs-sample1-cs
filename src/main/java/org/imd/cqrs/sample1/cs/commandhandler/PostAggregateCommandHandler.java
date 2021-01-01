package org.imd.cqrs.sample1.cs.commandhandler;

import lombok.RequiredArgsConstructor;
import org.imd.cqrs.sample1.cs.command.AbstractCommand;
import org.imd.cqrs.sample1.cs.command.post.CreatePostCommand;
import org.imd.cqrs.sample1.cs.command.post.DeletePostCommand;
import org.imd.cqrs.sample1.cs.command.post.UpdatePostCommand;
import org.imd.cqrs.sample1.cs.command.postcomment.AddPostCommentCommand;
import org.imd.cqrs.sample1.cs.command.postcomment.DeletePostCommentCommand;
import org.imd.cqrs.sample1.cs.command.postcomment.UpdatePostCommentCommand;
import org.imd.cqrs.sample1.cs.eventproducer.AggregateEventProducer;
import org.imd.cqrs.sample1.cs.exception.AggregateNotFoundException;
import org.imd.cqrs.sample1.es.store.AggregateStore;
import org.imd.cqrs.sample1.model.domain.aggregate.PostAggregate;
import org.imd.cqrs.sample1.model.domain.aggregate.event.PostAggregateChangedEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PostAggregateCommandHandler {

    private final AggregateStore<PostAggregate, UUID> aggregateStore;
    private final AggregateEventProducer<PostAggregateChangedEvent> aggregateEventProducer;

    public void handle(CreatePostCommand command) {
        final PostAggregate aggregate = new PostAggregate();
        doHandle(aggregate, command, PostAggregateChangedEvent.Status.CREATED);
    }

    public void handle(DeletePostCommand command) throws AggregateNotFoundException {
        final PostAggregate aggregate = findAggregate(command.getId());
        checkAggregate(command.getId().toString(), aggregate);
        doHandle(aggregate, command, PostAggregateChangedEvent.Status.DELETED);
    }

    public void handle(UpdatePostCommand command) throws AggregateNotFoundException {
        final PostAggregate aggregate = findAggregate(command.getId());
        checkAggregate(command.getId().toString(), aggregate);
        doHandle(aggregate, command, PostAggregateChangedEvent.Status.UPDATED);
    }

    public void handle(AddPostCommentCommand command) throws AggregateNotFoundException {
        final PostAggregate aggregate = findAggregate(command.getPostId());
        checkAggregate(command.getPostId().toString(), aggregate);
        doHandle(aggregate, command, PostAggregateChangedEvent.Status.UPDATED);
    }

    public void handle(DeletePostCommentCommand command) throws AggregateNotFoundException {
        final PostAggregate aggregate = findAggregate(command.getPostId());
        checkAggregate(command.getPostId().toString(), aggregate);
        doHandle(aggregate, command, PostAggregateChangedEvent.Status.UPDATED);
    }

    public void handle(UpdatePostCommentCommand command) throws AggregateNotFoundException {
        final PostAggregate aggregate = findAggregate(command.getPostId());
        checkAggregate(command.getPostId().toString(), aggregate);
        doHandle(aggregate, command, PostAggregateChangedEvent.Status.UPDATED);
    }

    private void checkAggregate(String postId, PostAggregate aggregate) throws AggregateNotFoundException {
        if (Objects.isNull(aggregate)) {
            throw new AggregateNotFoundException("Unable to find aggregate with id: " + postId);
        }
    }

    public void doHandle(final PostAggregate aggregate,
                         final AbstractCommand command,
                         final PostAggregateChangedEvent.Status status) {
        aggregate.handle(command);
        storeAggregate(aggregate);
        sendEvent(aggregate, status);
    }

    private PostAggregate findAggregate(UUID key) {
        return aggregateStore.findAggregate(key);
    }

    private void storeAggregate(PostAggregate aggregate) {
        aggregateStore.storeAggregate(aggregate);
    }

    private void sendEvent(PostAggregate aggregate, PostAggregateChangedEvent.Status status) {
        final PostAggregateChangedEvent event = new PostAggregateChangedEvent();
        event.setPost(aggregate.getState().getPost());
        event.setPostCommentList(aggregate.getState().getPostCommentList());
        event.setStatus(status);

        aggregateEventProducer.sendEvent(event);
    }
}
