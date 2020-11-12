package org.imd.cqrs.sample1.cs.escqrs.aggregates;

import lombok.RequiredArgsConstructor;
import org.imd.cqrs.sample1.cs.cqrs.commands.CreateUserCommand;
import org.imd.cqrs.sample1.cs.cqrs.commands.UpdateUserCommand;
import org.imd.cqrs.sample1.cs.es.events.Event;
import org.imd.cqrs.sample1.cs.es.events.UserAddressAddedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserAddressChangedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserAddressRemovedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserContactAddedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserContactChangedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserContactRemovedEvent;
import org.imd.cqrs.sample1.cs.es.events.UserCreatedEvent;
import org.imd.cqrs.sample1.cs.es.service.UserUtility;
import org.imd.cqrs.sample1.cs.model.Address;
import org.imd.cqrs.sample1.cs.model.Contact;
import org.imd.cqrs.sample1.cs.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserAggregator {

    private final UserUtility userUtility;

    public UserAggregateData handleCreateUserCommand(CreateUserCommand command) {
        UserCreatedEvent event = new UserCreatedEvent(command.getUserId(), command.getFirstName(), command.getLastName());
        return new UserAggregateData(command.getUserId(), Arrays.asList(event));
    }

    public UserAggregateData handleUpdateUserCommand(UpdateUserCommand command) {
        User user = userUtility.recreateUserState(command.getUserId());
        List<Event> events = new ArrayList<>();

        // contacts
        Set<Contact> contactsToAdd = new HashSet<> (command.getContacts());
        for (Contact sContact : user.getContacts()) {
            Contact tContact = findContact(command.getContacts(), sContact.getContactId());
            if (tContact == null) {
                UserContactRemovedEvent contactRemovedEvent =
                    new UserContactRemovedEvent(sContact.getContactId(), sContact.getType(), sContact.getDetail());
                events.add(contactRemovedEvent);
            } else {
                contactsToAdd.remove(tContact);

                if (tContact.equals(sContact)) {
                    // keep it. do nothing.
                } else {
                    UserContactChangedEvent contactChangedEvent = new UserContactChangedEvent(tContact.getContactId(), tContact.getType(), tContact.getDetail());
                    events.add(contactChangedEvent);
                }
            }
        }

        for (Contact contact : contactsToAdd) {
            UserContactAddedEvent contactAddedEvent = new UserContactAddedEvent(contact.getContactId(), contact.getType(), contact.getDetail());
            events.add(contactAddedEvent);
        }

        // addresses
        Set<Address> addressesToAdd = new HashSet<> (command.getAddresses());
        for (Address sAddress : user.getAddresses()) {
            Address tAddress = findAddress(command.getAddresses(), sAddress.getAddressId());
            if (tAddress == null) {
                UserAddressRemovedEvent addressRemovedEvent =
                        new UserAddressRemovedEvent(sAddress.getAddressId(), sAddress.getCity(), sAddress.getState(), sAddress.getPostcode());
                events.add(addressRemovedEvent);
            } else {
                addressesToAdd.remove(tAddress);

                if (tAddress.equals(sAddress)) {
                    // keep it. do nothing.
                } else {
                    UserAddressChangedEvent addressChangedEvent = new UserAddressChangedEvent(tAddress.getAddressId(), tAddress.getCity(), tAddress.getState(), tAddress.getPostcode());
                    events.add(addressChangedEvent);
                }
            }
        }

        for (Address address : addressesToAdd) {
            UserAddressAddedEvent addressAddedEvent = new UserAddressAddedEvent(address.getAddressId(), address.getCity(), address.getState(), address.getPostcode());;
            events.add(addressAddedEvent);
        }

        return new UserAggregateData(command.getUserId(), events);
    }

    private Address findAddress(Set<Address> addressSet, Long addressId) {
        if (addressSet == null) {
            return null;
        }

        Optional<Address> result = addressSet.
            stream().
            filter(a -> a.getAddressId().equals(addressId)).
            findFirst();

        return result.orElse(null);
    }

    private Contact findContact(Set<Contact> contactSet, Long contactId) {
        if (contactSet == null) {
            return null;
        }

        Optional<Contact> result = contactSet.
                stream().
                filter(c -> c.getContactId().equals(contactId)).
                findFirst();

        return result.orElse(null);
    }
}
