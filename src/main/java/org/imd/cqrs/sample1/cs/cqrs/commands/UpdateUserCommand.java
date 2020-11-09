package org.imd.cqrs.sample1.cs.cqrs.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.imd.cqrs.sample1.cs.model.Address;
import org.imd.cqrs.sample1.cs.model.Contact;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class UpdateUserCommand {

    private String userId;
    private Set<Address> addresses = new HashSet<>();
    private Set<Contact> contacts = new HashSet<>();

}
