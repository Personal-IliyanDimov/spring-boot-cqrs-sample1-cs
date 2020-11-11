package org.imd.cqrs.sample1.cs.jaxrs.service;

import org.imd.cqrs.sample1.cs.model.Address;
import org.imd.cqrs.sample1.cs.model.Contact;
import org.imd.cqrs.sample1.cs.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {


    @Transactional
    public User createUser(User user) {
        return null;
    }

    @Transactional
    public Address addUserAddress(Long userId, Address address) {
        return null;
    }

    @Transactional
    public Address changeUserAddress(Long userId, Long addressId, Address address) {
        return null;
    }

    @Transactional
    public Contact addUserContact(Long userId, Contact contact) {
        return null;
    }

    @Transactional
    public Contact changeUserContact(Long userId, Long contactId, Contact contact) {
        return null;
    }

}
