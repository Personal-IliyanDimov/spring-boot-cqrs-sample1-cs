package org.imd.cqrs.sample1.cs.cqrs.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDeleteCommand {
    private Long userId;
    private String firstName;
    private String lastName;
}
