package org.imd.cqrs.sample1.cs.es.service;



import org.imd.cqrs.sample1.cs.es.events.UserAddressAddedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserAddressRemovedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserContactAddedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserContactRemovedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserCreatedEvent;
import org.imd.cqrs.sample1.cs.es.repository.EventStore;
import org.imd.cqrs.sample1.cs.model.Address;
import org.imd.cqrs.sample1.cs.model.Contact;
import org.imd.cqrs.sample1.cs.model.User;

import java.util.Set;

public class UserService {

    private EventStore repository;

    public UserService(EventStore repository) {
        this.repository = repository;
    }

    public void createUser(String userId, String firstName, String lastName) {
        repository.appendEvents(userId, new UserCreatedEvent(userId, firstName, lastName));
    }

    public void updateUser(String userId, Set<Contact> contacts, Set<Address> addresses) throws Exception {
        User user = UserUtility.recreateUserState(repository, userId);
        if (user == null)
            throw new Exception("User does not exist.");

        user.getContacts()
            .stream()
            .filter(c -> !contacts.contains(c))
            .forEach(c -> repository.appendEvents(userId, new UserContactRemovedEvent(c.getContactId(), c.getType(), c.getDetail())));
        contacts.stream()
            .filter(c -> !user.getContacts()
                .contains(c))
            .forEach(c -> repository.appendEvents(userId, new UserContactAddedEvent(c.getContactId(), c.getType(), c.getDetail())));
        user.getAddresses()
            .stream()
            .filter(a -> !addresses.contains(a))
            .forEach(a -> repository.appendEvents(userId, new UserAddressRemovedEvent(a.getAddressId(), a.getCity(), a.getState(), a.getPostcode())));
        addresses.stream()
            .filter(a -> !user.getAddresses()
                .contains(a))
            .forEach(a -> repository.appendEvents(userId, new UserAddressAddedEvent(a.getAddressId(), a.getCity(), a.getState(), a.getPostcode())));
    }
}
