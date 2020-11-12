package org.imd.cqrs.sample1.cs.es.service;

import lombok.RequiredArgsConstructor;
import org.imd.cqrs.sample1.cs.es.events.Event;
import org.imd.cqrs.sample1.cs.es.events.useraddress.UserAddressAddedEvent;
import org.imd.cqrs.sample1.cs.es.events.useraddress.UserAddressChangedEvent;
import org.imd.cqrs.sample1.cs.es.events.useraddress.UserAddressRemovedEvent;
import org.imd.cqrs.sample1.cs.es.events.user.UserCreatedEvent;
import org.imd.cqrs.sample1.cs.es.store.EventStore;
import org.imd.cqrs.sample1.cs.model.Address;
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

        List<Event> events = eventStore.getUserEvents(userId);
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
                Address address = new Address(e.getAddressId());
                if (user != null)
                    user.getAddresses()
                            .remove(address);
            }
        }

        return user;
    }

}
