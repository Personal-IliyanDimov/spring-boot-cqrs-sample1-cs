package org.imd.cqrs.sample1.cs.es.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserContactAddedEvent extends Event {
    private String contactId;
    private String contactType;
    private String contactDetails;

}
