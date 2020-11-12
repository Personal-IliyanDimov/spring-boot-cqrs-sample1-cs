package org.imd.cqrs.sample1.cs.cqrs.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserCommand {

    private Long userId;
    private String firstName;
    private String lastName;

}
