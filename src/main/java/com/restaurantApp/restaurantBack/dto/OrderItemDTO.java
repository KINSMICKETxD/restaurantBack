package com.restaurantApp.restaurantBack.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTO {

    private Integer MenuItemId;

    private Integer quantity;

    private Double price;
}
