package com.restaurantApp.restaurantBack.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ReservationTimeValidator.class)
public @interface ValidReservationDate {

    String message() default "Reservation date must be between 2 hours and 30 days from now";

    // These are required parameters for custom annotations
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
