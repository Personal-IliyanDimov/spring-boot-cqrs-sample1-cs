package org.imd.cqrs.sample1.cs.command.post;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DeletePostCommand {
    private UUID id;
}
