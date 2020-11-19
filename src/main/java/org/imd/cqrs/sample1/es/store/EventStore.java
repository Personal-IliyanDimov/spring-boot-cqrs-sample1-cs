package org.imd.cqrs.sample1.es.store;

import org.imd.cqrs.sample1.es.event.Event;

import java.util.List;

public interface EventStore {
    public void appendUserEvents(Long id, Event event);
    public List<Event> getUserEvents(Long id);
}
