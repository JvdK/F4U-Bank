package com.bank.exception;

/**
 * Exception thrown when an invalid card/pin code combination is given.
 */
public class InvalidPINException extends Exception {
    public InvalidPINException(String message) {
        super(message);
    }
}