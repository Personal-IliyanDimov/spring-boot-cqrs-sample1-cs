package org.imd.cqrs.sample1.es.event;

import lombok.ToString;

import java.util.UUID;

@ToString
public abstract class Event {

    public final UUID id = UUID.randomUUID();

}
