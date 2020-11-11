package org.imd.cqrs.sample1.cs.jaxrs.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserAlreadyExistsException extends Exception {
    private final String firstName;
    private final String lastName;
}
