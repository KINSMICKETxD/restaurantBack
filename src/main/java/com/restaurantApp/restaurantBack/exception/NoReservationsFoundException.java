package com.restaurantApp.restaurantBack.exception;

public class NoReservationsFoundException extends RuntimeException {
    public NoReservationsFoundException(String message) {
        super(message);
    }
}
