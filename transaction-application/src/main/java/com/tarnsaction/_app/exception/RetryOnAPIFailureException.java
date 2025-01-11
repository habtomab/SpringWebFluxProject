package com.tarnsaction._app.exception;

public class RetryOnAPIFailureException extends RuntimeException {

    // Constructor with a message and cause
    public RetryOnAPIFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with just a message
    public RetryOnAPIFailureException(String message) {
        super(message);
    }

    // Constructor with just a cause
    public RetryOnAPIFailureException(Throwable cause) {
        super(cause);
    }
}

