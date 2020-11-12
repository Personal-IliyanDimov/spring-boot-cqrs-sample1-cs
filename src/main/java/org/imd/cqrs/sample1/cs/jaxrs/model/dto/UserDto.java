package org.imd.cqrs.sample1.cs.jaxrs.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String userid;
    private String firstname;
    private String lastname;
}
