package org.imd.cqrs.sample1.cs.jaxrs.model.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NonNull
    private String userid;
    @NonNull
    private String firstname;
    @NonNull
    private String lastname;
}
