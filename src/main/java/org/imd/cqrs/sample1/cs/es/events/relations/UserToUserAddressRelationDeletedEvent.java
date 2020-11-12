package org.imd.cqrs.sample1.cs.es.events.relations;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserToUserAddressRelationDeletedEvent {
    private Long userId;
    private Long userAddressId;
}
