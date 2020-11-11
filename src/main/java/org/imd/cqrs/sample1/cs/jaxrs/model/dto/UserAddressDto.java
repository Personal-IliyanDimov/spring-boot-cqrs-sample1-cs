package org.imd.cqrs.sample1.cs.jaxrs.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressDto {
    private String addressId;
    private String city;
    private String state;
    private String postcode;
}
