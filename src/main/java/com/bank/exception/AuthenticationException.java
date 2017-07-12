package com.bank.exception;

/**
 * Exception thrown when the authentication has failed, e.g. wrong username/password combination.
 */
public class AuthenticationException extends Exception {
    public AuthenticationException(String message) {
        super(message);
    }
}