package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when the user tries to access data when the user is not logged in.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotLoggedInException extends Exception {
}
