package org.imd.cqrs.sample1.cs.es.events.useraddress;

import lombok.Getter;
import lombok.Setter;
import org.imd.cqrs.sample1.cs.es.events.Event;

@Getter
@Setter
public class UserAddressAddedEvent extends Event {
    private Long addressId;
    private String city;
    private String state;
    private String postCode;

}
