package com.example.be_swp.Exceptions;

public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(String message ) {
        super(message);
    }
}
