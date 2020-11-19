package org.imd.cqrs.sample1.cs.command.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateCommand {
    private Long id;
    private String title;
}
