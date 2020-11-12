package org.imd.cqrs.sample1.cs.escqrs.aggregates;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.imd.cqrs.sample1.cs.es.events.Event;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserAggregateData {
    private final Long userId;
    private final List<Event> eventList;
}
