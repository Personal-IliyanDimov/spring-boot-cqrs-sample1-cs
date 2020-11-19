package org.imd.cqrs.sample1.jaxrs.exception.post;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class PostNotFoundException extends Exception {
    private final Long id;

}
