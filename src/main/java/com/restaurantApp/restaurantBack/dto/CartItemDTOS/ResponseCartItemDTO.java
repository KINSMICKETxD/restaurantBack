package com.restaurantApp.restaurantBack.dto.CartItemDTOS;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCartItemDTO {


    private int cartItemId;

    @NotNull(message = "menu item name cannot be NULL.")
    private String menuItemName;

    @NotNull(message = "Cart Id cannot be NULL.")
    private int cartId;

    @Min(value = 1,message = "Quantity must be at least 1 ")
    private int quantity;

    @Min(value = 0,message = "Total Price cannot  be negative.")
    private double totalPrice;

    private String customization;

}
