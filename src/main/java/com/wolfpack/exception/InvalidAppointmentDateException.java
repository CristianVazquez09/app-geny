package com.wolfpack.exception;

public class InvalidAppointmentDateException extends RuntimeException{

    public InvalidAppointmentDateException(String message) {
        super(message);
    }
}
