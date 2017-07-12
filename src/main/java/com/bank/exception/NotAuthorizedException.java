package com.bank.exception;

/**
 * Exception thrown when an authenticated user has no permission to perform an action.
 */
public class NotAuthorizedException extends Exception {
    public NotAuthorizedException(String message) {
        super(message);
    }
}