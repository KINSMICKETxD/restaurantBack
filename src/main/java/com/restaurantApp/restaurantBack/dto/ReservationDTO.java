package com.restaurantApp.restaurantBack.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReservationDTO {


    private int reservationId;

    private int customerId;

    private String  userName;

    private int tableId;

    private int tableNumber;

    private LocalDateTime reservationDateBegin;

    private LocalDateTime reservationDateEnd;

    private int numberOfGuests;

    private String specialInstruction;
}
