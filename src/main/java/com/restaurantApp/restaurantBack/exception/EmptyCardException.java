package com.restaurantApp.restaurantBack.exception;

public class EmptyCardException extends RuntimeException {
    public EmptyCardException(String message) {
        super(message);
    }
}
