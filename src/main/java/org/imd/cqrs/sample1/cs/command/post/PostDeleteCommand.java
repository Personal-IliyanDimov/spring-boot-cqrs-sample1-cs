package org.imd.cqrs.sample1.cs.command.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDeleteCommand {
    private Long id;
}
