package org.imd.cqrs.sample1.cs.es.service;

import lombok.RequiredArgsConstructor;
import org.imd.cqrs.sample1.cs.es.events.Event;
import org.imd.cqrs.sample1.cs.es.events.UserAddressAddedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserAddressChangedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserAddressRemovedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserContactAddedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserContactChangedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserContactRemovedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserCreatedEvent;
import org.imd.cqrs.sample1.cs.es.store.EventStore;
import org.imd.cqrs.sample1.cs.model.Address;
import org.imd.cqrs.sample1.cs.model.Contact;
import org.imd.cqrs.sample1.cs.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserUtility {

    private final EventStore eventStore;

    public User recreateUserState(Long userId) {
        User user = null;

        List<Event> events = eventStore.getEvents(userId);
        for (Event event : events) {
            if (event instanceof UserCreatedEvent) {
                UserCreatedEvent e = (UserCreatedEvent) event;
                user = new User(UUID.randomUUID()
                    .toString(), e.getFirstName(), e.getLastName());
            }
            if (event instanceof UserAddressAddedEvent) {
                UserAddressAddedEvent e = (UserAddressAddedEvent) event;
                Address address = new Address(e.getAddressId(), e.getCity(), e.getState(), e.getPostCode());
                if (user != null)
                    user.getAddresses()
                        .add(address);
            }
            if (event instanceof UserAddressChangedEvent) {
                UserAddressChangedEvent e = (UserAddressChangedEvent) event;
                if (user != null) {
                    user.getAddresses()
                        .stream()
                        .filter(a -> a.getAddressId().equals(e.getAddressId()))
                        .findFirst()
                        .ifPresent(a -> {
                            a.setCity(e.getCity());
                            a.setPostcode(e.getPostCode());
                            a.setState(e.getState());
                        });
                }
            }
            if (event instanceof UserAddressRemovedEvent) {
                UserAddressRemovedEvent e = (UserAddressRemovedEvent) event;
                Address address = new Address(e.getAddressId(), e.getCity(), e.getState(), e.getPostCode());
                if (user != null)
                    user.getAddresses()
                        .remove(address);
            }

            if (event instanceof UserContactAddedEvent) {
                UserContactAddedEvent e = (UserContactAddedEvent) event;
                Contact contact = new Contact(e.getContactId(), e.getContactType(), e.getContactDetails());
                if (user != null)
                    user.getContacts()
                        .add(contact);
            }
            if (event instanceof UserContactChangedEvent) {
                UserContactChangedEvent e = (UserContactChangedEvent) event;
                if (user != null) {
                    user.getContacts()
                            .stream()
                            .filter(c -> c.getContactId().equals(e.getContactId()))
                            .findFirst()
                            .ifPresent(c -> {
                                c.setDetail(e.getContactDetails());
                                c.setType(e.getContactType());
                            });
                }
            }
            if (event instanceof UserContactRemovedEvent) {
                UserContactRemovedEvent e = (UserContactRemovedEvent) event;
                Contact contact = new Contact(e.getContactId(), e.getContactType(), e.getContactDetails());
                if (user != null)
                    user.getContacts()
                        .remove(contact);
            }
        }

        return user;
    }

}
