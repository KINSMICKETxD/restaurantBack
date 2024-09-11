package com.restaurantApp.restaurantBack.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantTableDTO {


    @NotNull
    private int id;

    @NotNull
    @Min(value = 1,message = "Invalid Table Number")
    private int tableNumber;


    private String desc;

    @Min(value = 1,message = "seating Capacity cannot be less than 1")
    private int seatingCapacity;


    private boolean isAvailable;

}
