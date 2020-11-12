package org.imd.cqrs.sample1.cs.cqrs.commands;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.cs.model.Address;

@Getter
@Setter
public class UserAddressCreateCommand {
    private Long userId;
    private Long addressId;
    private Address addresses;
}
