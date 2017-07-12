package com.bank.exception;

/**
 * Exceptions thrown when one or more parameters contain invalid values.
 */
public class InvalidParamValueException extends Exception {
    public InvalidParamValueException(String message) {
        super(message);
    }
}