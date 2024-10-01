package com.restaurantApp.restaurantBack.exception;

public class MaxActiveReservationException extends RuntimeException {
    public MaxActiveReservationException(String message) {
        super(message);
    }
}
