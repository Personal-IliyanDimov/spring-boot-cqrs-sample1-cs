package org.imd.cqrs.sample1.cs.es.store;

import org.imd.cqrs.sample1.cs.es.events.Event;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersistentEventStore implements EventStore {

    @Override
    public void appendEvents(String id, Event event) {

    }

    @Override
    public List<Event> getEvents(String id) {
        return null;
    }
}
