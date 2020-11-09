package org.imd.cqrs.sample1.cs.cqrs.repository;

import org.imd.cqrs.sample1.cs.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserWriteRepository {

    private Map<String, User> store = new HashMap<>();

    public void addUser(String id, User user) {
        store.put(id, user);
    }

    public User getUser(String id) {
        return store.get(id);
    }
}
