package org.imd.cqrs.sample1.es.event;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class Event {

    private LocalDateTime whenOccurs;

}
