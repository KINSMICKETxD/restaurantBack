package com.restaurantApp.restaurantBack.exception;

public class NoTableAvailableException extends RuntimeException {
    public NoTableAvailableException(String message) {
        super(message);
    }
}
