package org.imd.cqrs.sample1.cs.command.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeletePostCommand {
    private Long id;
}
