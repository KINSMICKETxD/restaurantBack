package com.restaurantApp.restaurantBack.dto;

import com.restaurantApp.restaurantBack.Validation.ValidReservationDate;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.After;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ValidReservationDate
public class CreateReservationDTO {

    @NotNull
    @FutureOrPresent(message = "please should a valid reservation Date begin")
    private LocalDateTime reservationDateBegin;

    @NotNull
    private LocalDateTime reservationDateEnd;

    @NotNull
    @Min(value = 1,message = "please enter a valid Number of People.")
    private int numberOfPeople;

    private String specialInstructions;
}
