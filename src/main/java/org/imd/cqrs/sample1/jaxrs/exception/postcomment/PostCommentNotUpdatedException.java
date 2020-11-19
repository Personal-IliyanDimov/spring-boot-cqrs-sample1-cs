package org.imd.cqrs.sample1.jaxrs.exception.postcomment;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostCommentNotUpdatedException extends Exception {
    public PostCommentNotUpdatedException(Exception causedBy) {
        super(causedBy);
    }
}
