package com.restaurantApp.restaurantBack.Validation;

import com.restaurantApp.restaurantBack.dao.RestaurantTableDAO;
import com.restaurantApp.restaurantBack.dto.CreateReservationDTO;
import com.restaurantApp.restaurantBack.entity.Reservation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;

public class ReservationTimeValidator implements ConstraintValidator<ValidReservationDate, CreateReservationDTO> {

    private static final Duration MIN_ADVANCE_DURATION = Duration.ofHours(2);
    private static final Duration MAX_ADVANCE_DURATION = Duration.ofDays(30);


    @Override
    public boolean isValid(CreateReservationDTO reservation, ConstraintValidatorContext context) {

        LocalDateTime reservationDateBegin = reservation.getReservationDateBegin();
        LocalDateTime reservationDateEnd = reservation.getReservationDateEnd();



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
        return true;

    }


}
