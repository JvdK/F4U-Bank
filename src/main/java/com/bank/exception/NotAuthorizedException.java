package com.bank.exception;

/**
 * Not authenticated
 */
public class NotAuthorizedException extends Exception {
    public NotAuthorizedException(String message) {
        super(message);
    }
}
