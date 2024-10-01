package com.restaurantApp.restaurantBack.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.restaurantApp.restaurantBack.dto.CartItemDTOS.ResponseCartItemDTO;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO extends RepresentationModel<CartDTO> {

    @NotNull
    private int cartId;

    @JsonProperty("cartItemsV1")
    private List<ResponseCartItemDTO> cartItems;

    private String totalPrice;
}
