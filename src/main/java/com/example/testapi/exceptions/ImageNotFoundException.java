package com.example.testapi.exceptions;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(String message) {
        super(message);
    }
}
