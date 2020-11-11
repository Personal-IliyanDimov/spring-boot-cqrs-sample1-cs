package org.imd.cqrs.sample1.cs.es.repository;

import org.imd.cqrs.sample1.cs.es.events.Event;

import java.util.List;

public interface EventStore {
    public void appendEvents(String id, Event event);
    public List<Event> getEvents(String id);
}
