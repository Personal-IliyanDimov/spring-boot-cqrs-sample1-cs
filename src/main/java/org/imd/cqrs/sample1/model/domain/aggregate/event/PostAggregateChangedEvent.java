package org.imd.cqrs.sample1.model.domain.aggregate.event;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.model.domain.Post;
import org.imd.cqrs.sample1.model.domain.PostComment;

import java.util.List;

@Getter
@Setter
public class PostAggregateChangedEvent {
    private Status status;

    private Post post;
    private List<PostComment> postCommentList;

    public enum Status {
        CREATED("C"),
        UPDATED("U"),
        DELETED("D");

        private String s;

        private Status(String s) {
            this.s = s;
        }

        public String getS() {
            return s;
        }
    }
}
