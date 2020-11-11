package org.imd.cqrs.sample1.cs.es.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UserAddressChangedEvent extends Event {
    private String addressId;
    private String city;
    private String state;
    private String postCode;

}
