package com.restaurantApp.restaurantBack.exception;

public class SeatingCapacityExceededException extends RuntimeException {
    public SeatingCapacityExceededException(String message) {
        super(message);
    }
}
