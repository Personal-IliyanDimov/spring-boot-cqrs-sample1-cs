package org.imd.cqrs.sample1.cs.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
    private Long addressId;
    private String city;
    private String state;
    private String postcode;
}
