package com.eligibility.customExceptions;

public class RetryOnAPIFailureException extends RuntimeException {
    public RetryOnAPIFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}