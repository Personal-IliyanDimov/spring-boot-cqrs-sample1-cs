package org.imd.cqrs.sample1.cs.command.post;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.cs.command.AbstractCommand;

import java.util.UUID;

@Getter
@Setter
public class DeletePostCommand extends AbstractCommand {
    private UUID id;
}
