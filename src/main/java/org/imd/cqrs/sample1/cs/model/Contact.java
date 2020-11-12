package org.imd.cqrs.sample1.cs.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
    private Long contactId;
    private String type;
    private String detail;

}
