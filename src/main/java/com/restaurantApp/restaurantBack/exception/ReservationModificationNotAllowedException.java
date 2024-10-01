package com.restaurantApp.restaurantBack.exception;

public class ReservationModificationNotAllowedException extends RuntimeException {
  public ReservationModificationNotAllowedException(String message) {
    super(message);
  }
}
