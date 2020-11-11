package org.imd.cqrs.sample1.cs.jaxrs.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserNotFoundException extends Exception {
    private final Long id;

}
