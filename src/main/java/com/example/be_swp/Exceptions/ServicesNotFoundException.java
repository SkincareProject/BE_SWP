package com.example.be_swp.Exceptions;

public class ServicesNotFoundException extends RuntimeException {
    public ServicesNotFoundException(String message) {
        super(message);
    }
}
