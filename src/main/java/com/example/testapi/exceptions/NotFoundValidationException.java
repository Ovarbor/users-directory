package com.example.TestAPI.exceptions;

public class NotFoundValidationException extends RuntimeException {
    public NotFoundValidationException(String message) {
        super(message);
    }
}
