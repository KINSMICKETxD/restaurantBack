package com.restaurantApp.restaurantBack.exception;

public class TableIsReservedException extends RuntimeException {
    public TableIsReservedException(String message) {
        super(message);
    }
}
