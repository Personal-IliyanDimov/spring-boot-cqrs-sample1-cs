package org.imd.cqrs.sample1.cs.command.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostCommand {
    private Long id;
    private String title;
}
