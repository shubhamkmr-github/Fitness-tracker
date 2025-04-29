package com.fitness.userservice.exception;

public class ExistingUserException extends RuntimeException {

    public ExistingUserException(String message) {
        super(message);
    }
}
