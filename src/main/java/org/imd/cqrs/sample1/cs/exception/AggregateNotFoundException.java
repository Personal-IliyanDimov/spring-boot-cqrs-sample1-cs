package org.imd.cqrs.sample1.cs.exception;

public class AggregateNotFoundException extends Exception {

    public AggregateNotFoundException(String message) {
        super(message);
    }

    public AggregateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AggregateNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AggregateNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
