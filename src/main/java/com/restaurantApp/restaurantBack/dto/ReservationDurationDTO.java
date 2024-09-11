package com.restaurantApp.restaurantBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDurationDTO {


    private LocalDateTime beginTime;


    private LocalDateTime endTime;
}
