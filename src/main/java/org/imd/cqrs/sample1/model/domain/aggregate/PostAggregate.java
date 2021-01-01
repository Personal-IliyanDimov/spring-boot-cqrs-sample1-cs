package org.imd.cqrs.sample1.model.domain.aggregate;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.cs.command.AbstractCommand;
import org.imd.cqrs.sample1.cs.command.post.CreatePostCommand;
import org.imd.cqrs.sample1.cs.command.post.DeletePostCommand;
import org.imd.cqrs.sample1.cs.command.post.UpdatePostCommand;
import org.imd.cqrs.sample1.cs.command.postcomment.AddPostCommentCommand;
import org.imd.cqrs.sample1.cs.command.postcomment.DeletePostCommentCommand;
import org.imd.cqrs.sample1.cs.command.postcomment.UpdatePostCommentCommand;
import org.imd.cqrs.sample1.es.event.Event;
import org.imd.cqrs.sample1.es.event.post.PostCreatedEvent;
import org.imd.cqrs.sample1.es.event.post.PostDeletedEvent;
import org.imd.cqrs.sample1.es.event.post.PostUpdatedEvent;
import org.imd.cqrs.sample1.es.event.postcomment.PostCommentAddedEvent;
import org.imd.cqrs.sample1.es.event.postcomment.PostCommentDeletedEvent;
import org.imd.cqrs.sample1.es.event.postcomment.PostCommentUpdatedEvent;
import org.imd.cqrs.sample1.model.domain.Post;
import org.imd.cqrs.sample1.model.domain.PostComment;
import org.imd.cqrs.sample1.model.mapper.CommandToEventMapper;
import org.imd.cqrs.sample1.service.domain.AbstractCastingBiConsumer;
import org.imd.cqrs.sample1.service.domain.CastingBiConsumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PostAggregate extends AbstractAggregate {
    private static Map<Class<? extends Event>, CastingBiConsumer<PostAggregateState>> stateMutatorMap;
    static {
        stateMutatorMap = new HashMap<>();
        stateMutatorMap.put(PostCreatedEvent.class, new PostCreatedEventHandler(PostCreatedEvent.class));
        stateMutatorMap.put(PostDeletedEvent.class, new PostDeletedEventHandler(PostDeletedEvent.class));
        stateMutatorMap.put(PostUpdatedEvent.class, new PostUpdatedEventHandler(PostUpdatedEvent.class));
        stateMutatorMap.put(PostCommentAddedEvent.class, new PostCommentAddedEventHandler(PostCommentAddedEvent.class));
        stateMutatorMap.put(PostCommentDeletedEvent.class, new PostCommentDeletedEventHandler(PostCommentDeletedEvent.class));
        stateMutatorMap.put(PostCommentUpdatedEvent.class, new PostCommentUpdatedEventHandler(PostCommentUpdatedEvent.class));
    }

    private final CommandToEventMapper mapper;
    private PostAggregateState state;

    public PostAggregate() {
        super();
        mapper = CommandToEventMapper.INSTANCE;
        state = new PostAggregateState();
    }

    @Override
    public void initState(long sourceVersion, List<Event> eventList) {
        super.initState(sourceVersion, eventList);
    }

    public PostAggregateState getState() {
        return state;
    }

    public PostAggregate handle(AbstractCommand command) {
        PostAggregate result = null;

        if (command instanceof CreatePostCommand) {
            result = doHandle((CreatePostCommand) command);
        }
        else if (command instanceof DeletePostCommand) {
            result = doHandle((CreatePostCommand) command);
        }
        else if (command instanceof UpdatePostCommand) {
            result = doHandle((UpdatePostCommand) command);
        }
        if (command instanceof AddPostCommentCommand) {
            result = doHandle((AddPostCommentCommand) command);
        }
        else if (command instanceof DeletePostCommentCommand) {
            result = doHandle((DeletePostCommentCommand) command);
        }
        else if (command instanceof UpdatePostCommentCommand) {
            result = doHandle((UpdatePostCommentCommand) command);
        }
        else {
            throw new IllegalStateException("Unsupported command type: " + command.getClass().getName());
        }


        return result;
    }

    private PostAggregate doHandle(CreatePostCommand command) {
        final PostCreatedEvent event = mapper.toEvent(command);
        apply(event);
        return this;
    }

    private PostAggregate doHandle(DeletePostCommand command) {
        final PostDeletedEvent event = mapper.toEvent(command);
        apply(event);
        return this;
    }

    private PostAggregate doHandle(UpdatePostCommand command) {
        final PostUpdatedEvent event = mapper.toEvent(command);
        apply(event);
        return this;
    }

    private PostAggregate doHandle(AddPostCommentCommand command) {
        final PostCommentAddedEvent event = mapper.toEvent(command);
        apply(event);
        return this;
    }

    private PostAggregate doHandle(DeletePostCommentCommand command) {
        final PostCommentDeletedEvent event = mapper.toEvent(command);
        apply(event);
        return this;
    }

    private PostAggregate doHandle(UpdatePostCommentCommand command) {
        final PostCommentUpdatedEvent event = mapper.toEvent(command);
        apply(event);
        return this;
    }

    @Override
    protected void doApply(Event event) {
        if (stateMutatorMap.containsKey(event.getClass())) {
            stateMutatorMap.get(event.getClass()).accept(event, state);
        } else {
            throw new IllegalStateException("Unsupported event type: " + event.getClass().getName());
        }
    }

    private static class PostCreatedEventHandler extends AbstractCastingBiConsumer<PostCreatedEvent, PostAggregateState> {
        private Helper helper;

        public PostCreatedEventHandler(Class<PostCreatedEvent> clazz) {
            super(clazz);
            helper = new Helper();
        }

        @Override
        public void doAccept(PostCreatedEvent event, PostAggregateState state) {
            if (! helper.postExists(state)) {
                final Post post = new Post();
                post.setId(event.getId().toString());
                post.setTitle(event.getTitle());

                state.setPost(post);
                state.setPostCreated(true);
                state.setPostDeleted(false);

                return ;
            }

            throw new IllegalStateException("Post already exists or was already deleted. ");
        }
    }

    private static class PostDeletedEventHandler extends AbstractCastingBiConsumer<PostDeletedEvent, PostAggregateState>  {
        private Helper helper;

        public PostDeletedEventHandler(Class<PostDeletedEvent> clazz) {
            super(clazz);
            helper = new Helper();
        }

        @Override
        public void doAccept(PostDeletedEvent event, PostAggregateState state) {
            if ((helper.postExists(state)) && (helper.postIsNotDeleted(state))) {
                state.setPostDeleted(true);

                return ;
            }

            throw new IllegalStateException("Post does not exist or was already deleted. ");
        }
    }

    private static class PostUpdatedEventHandler extends AbstractCastingBiConsumer<PostUpdatedEvent, PostAggregateState>  {
        private Helper helper;

        public PostUpdatedEventHandler(Class<PostUpdatedEvent> clazz) {
            super(clazz);
            helper = new Helper();
        }

        @Override
        public void doAccept(PostUpdatedEvent event, PostAggregateState state) {
            if ((helper.postExists(state)) && (helper.postIsNotDeleted(state))) {
                final Post post = state.getPost();
                post.setId(event.getId().toString());
                post.setTitle(event.getTitle());

                return ;
            }

            throw new IllegalStateException("Post does not exist or was already deleted. ");
        }
    }

    private static class PostCommentAddedEventHandler extends AbstractCastingBiConsumer<PostCommentAddedEvent, PostAggregateState> {
        private Helper helper;

        public PostCommentAddedEventHandler(Class<PostCommentAddedEvent> clazz) {
            super(clazz);
            helper = new Helper();
        }

        @Override
        public void doAccept(PostCommentAddedEvent event, PostAggregateState state) {
            if (helper.postExists(state) && (helper.postIsNotDeleted(state))) {
                if (! helper.postCommentExists(state, event.getId().toString())) {
                    final PostComment pc = new PostComment();
                    pc.setId(event.getId().toString());
                    pc.setReview(event.getReview());

                    state.getPostCommentList().add(pc);
                    return ;
                }

                throw new IllegalStateException("Post comment already exists. ");
            }

            throw new IllegalStateException("Post already exists or was already deleted. ");
        }
    }

    private static class PostCommentDeletedEventHandler extends AbstractCastingBiConsumer<PostCommentDeletedEvent, PostAggregateState> {
        private Helper helper;

        public PostCommentDeletedEventHandler(Class<PostCommentDeletedEvent> clazz) {
            super(clazz);
            helper = new Helper();
        }

        @Override
        public void doAccept(PostCommentDeletedEvent event, PostAggregateState state) {
            if (helper.postExists(state) && (helper.postIsNotDeleted(state))) {
                if (helper.postCommentExists(state, event.getId().toString())) {
                    state.getPostCommentList().removeIf(pc -> pc.getId().equals(event.getId().toString()));

                    return ;
                }
            }

            throw new IllegalStateException("Post already exists or was already deleted. ");
        }
    }

    private static class PostCommentUpdatedEventHandler extends AbstractCastingBiConsumer<PostCommentUpdatedEvent, PostAggregateState> {
        private Helper helper;

        public PostCommentUpdatedEventHandler(Class<PostCommentUpdatedEvent> clazz) {
            super(clazz);
            helper = new Helper();
        }

        @Override
        public void doAccept(PostCommentUpdatedEvent event, PostAggregateState state) {
            if (helper.postExists(state) && (helper.postIsNotDeleted(state))) {
                final Optional<PostComment> firstOptional = state.getPostCommentList().stream()
                    .filter(pc -> pc.getId().equals(event.getId().toString()))
                    .findFirst();

                if (firstOptional.isPresent()) {
                    final PostComment first = firstOptional.get();
                    first.setId(event.getId().toString());
                    first.setReview(event.getReview());

                    return ;
                }

                throw new IllegalStateException("Post comment is not found. ");
            }

            throw new IllegalStateException("Post already exists or was already deleted. ");
        }
    }

    private static class Helper {

        public boolean postExists(PostAggregateState state) {
            return state.isPostCreated();
        }

        public boolean postIsNotDeleted(PostAggregateState state) {
            return ! state.isPostDeleted();
        }

        public boolean postCommentExists(PostAggregateState state, String id) {
            boolean result = false;

            final Optional<PostComment> first = state.getPostCommentList().stream()
                .filter(pc -> pc.getId().equals(id))
                .findFirst();

            return first.isPresent();
        }
    }

    @Getter
    @Setter
    public static class PostAggregateState {
        private boolean postCreated;
        private boolean postDeleted;

        private Post post;
        private List<PostComment> postCommentList;
    }
}
