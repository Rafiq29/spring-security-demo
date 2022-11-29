package com.herb.springsecuritydemo.error;

public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
