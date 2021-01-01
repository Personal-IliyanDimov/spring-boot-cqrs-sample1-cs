package org.imd.cqrs.sample1.cs.command.postcomment;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.cs.command.AbstractCommand;

import java.util.UUID;

@Getter
@Setter
public class AddPostCommentCommand extends AbstractCommand {
    private UUID postId;
    private UUID postCommentId;
    private String review;
}
