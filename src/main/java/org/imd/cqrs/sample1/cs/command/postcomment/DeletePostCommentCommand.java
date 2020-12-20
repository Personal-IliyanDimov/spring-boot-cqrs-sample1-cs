package org.imd.cqrs.sample1.cs.command.postcomment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletePostCommentCommand {
    private Long postId;
    private Long postCommentId;
}
