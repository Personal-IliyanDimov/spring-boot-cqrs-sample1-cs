package org.imd.cqrs.sample1.jaxrs.exception.post;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostNotUpdatedException extends Exception {
    public PostNotUpdatedException(Exception causedBy) {
        super(causedBy);
    }
}
