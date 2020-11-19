package org.imd.cqrs.sample1.cs.aggregate;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.model.domain.Post;
import org.imd.cqrs.sample1.model.domain.PostComment;

import java.util.List;

@Getter
@Setter
public class PostAggregate {

    private Post post;
    private List<PostComment> postCommentList;

}
