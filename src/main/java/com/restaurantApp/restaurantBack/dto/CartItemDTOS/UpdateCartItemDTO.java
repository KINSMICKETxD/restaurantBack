package com.restaurantApp.restaurantBack.dto.CartItemDTOS;


import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateCartItemDTO {

    @Min(value = 1,message = "Quantity must be at least 1.")
    private int quantity;

    private String customization;
}
