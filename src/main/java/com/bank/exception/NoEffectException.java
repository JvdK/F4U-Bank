package com.bank.exception;

/**
 * Exception thrown when action has no effect.
 */
public class NoEffectException extends Exception {
    public NoEffectException(String message) {
        super(message);
    }
}