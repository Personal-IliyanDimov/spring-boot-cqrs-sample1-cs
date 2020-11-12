package org.imd.cqrs.sample1.cs.cqrs.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressDeleteCommand {
    private Long userId;
    private Long addressId;
}
