package org.imd.cqrs.sample1.cs.es.store;

import org.imd.cqrs.sample1.cs.es.events.Event;

import java.util.List;

public interface EventStore {
    public void appendUserEvents(Long id, Event event);
    public List<Event> getUserEvents(Long id);
}
