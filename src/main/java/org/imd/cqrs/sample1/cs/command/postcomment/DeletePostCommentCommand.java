package org.imd.cqrs.sample1.cs.command.postcomment;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeletePostCommentCommand {
    private UUID postId;
    private UUID postCommentId;
}
