package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when the authentication has failed.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class AuthenticationException extends Exception {
    public AuthenticationException(String message) {
        super(message);
    }
}
