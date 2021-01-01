package org.imd.cqrs.sample1.cs.eventproducer;

import org.imd.cqrs.sample1.model.domain.aggregate.event.PostAggregateChangedEvent;
import org.springframework.stereotype.Component;

@Component
public class PostAggregateEventProducer implements AggregateEventProducer<PostAggregateChangedEvent> {

    @Override
    public void sendEvent(PostAggregateChangedEvent event) {
        System.out.println("Event: " + event);
    }
}
