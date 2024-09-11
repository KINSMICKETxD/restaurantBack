package com.restaurantApp.restaurantBack.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItemDTO {


    private int cartItemId;

    @NotNull(message = "Menu Item Name cannot be NULL")
    private String menuItemName;

    @NotNull(message = "Menu Item Id cannot be NULL")
    private int menuItemId;

    @Min(value = 1,message = "Quantity must be at least 1 ")
    private int quantity;

    private String customization;
}
