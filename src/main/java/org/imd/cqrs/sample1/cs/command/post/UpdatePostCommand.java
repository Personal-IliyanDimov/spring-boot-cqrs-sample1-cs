package org.imd.cqrs.sample1.cs.command.post;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.cs.command.AbstractCommand;

import java.util.UUID;

@Getter
@Setter
public class UpdatePostCommand extends AbstractCommand {
    private UUID id;
    private String title;
}
