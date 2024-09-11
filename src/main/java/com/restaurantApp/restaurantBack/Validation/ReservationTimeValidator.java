package com.restaurantApp.restaurantBack.Validation;

import com.restaurantApp.restaurantBack.dao.RestaurantTableDAO;
import com.restaurantApp.restaurantBack.entity.Reservation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;

public class ReservationTimeValidator implements ConstraintValidator<ValidReservationDate, Reservation> {

    private static final Duration MIN_ADVANCE_DURATION = Duration.ofHours(2);
    private static final Duration MAX_ADVANCE_DURATION = Duration.ofDays(30);


    @Override
    public boolean isValid(Reservation reservation, ConstraintValidatorContext context) {

        LocalDateTime reservationDateBegin = reservation.getReservationDateTimeBegin();
        LocalDateTime reservationDateEnd = reservation.getReservationDateTimeEnd();



        LocalDateTime now = LocalDateTime.now();

        if(reservationDateBegin.isBefore(now.plus(MIN_ADVANCE_DURATION))){
            context.buildConstraintViolationWithTemplate("Reservation start time must be at least 2 hours from now.")
                    .addPropertyNode("ReservationDateTimeBegin")
                    .addConstraintViolation();

            return false;
        }

        if(reservationDateBegin.isAfter(now.plus(MAX_ADVANCE_DURATION))){
            context.buildConstraintViolationWithTemplate("Reservation start time cannot be more than 30 days in advance")
                    .addPropertyNode("reservationDateTimeBegin")
                    .addConstraintViolation();

            return false;
        }

        if(reservationDateEnd.isBefore(reservationDateBegin)){
            context.buildConstraintViolationWithTemplate("Reservation end time cannot be Before the start time")
                    .addPropertyNode("reservationDateTimeEnd")
                    .addConstraintViolation();
            return false;
        }
        Duration duration = Duration.between(reservationDateBegin,reservationDateEnd);

        if(duration.toHours() > 2){
            context.buildConstraintViolationWithTemplate("Reservation duration cannot exceed 2 hours")
                    .addPropertyNode("reservationDateTimeEnd")
                    .addConstraintViolation();

            return false;
        }
        if(reservation.getGuestNumber() > reservation.getTable().getSeatingCapacity()){
            context.buildConstraintViolationWithTemplate("Number of Guests exceed The Table Capacity ("+reservation.getTable().getSeatingCapacity()+")")
                    .addPropertyNode("numberOfGuests")
                    .addConstraintViolation();

            return false;
        }

        return true;

    }
}
