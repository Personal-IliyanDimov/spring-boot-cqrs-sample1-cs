package org.imd.cqrs.sample1.cs.jaxrs.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserContactDto {
    private String contactId;
    private String type;
    private String detail;
}
