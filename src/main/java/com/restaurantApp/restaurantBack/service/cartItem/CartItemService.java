package com.restaurantApp.restaurantBack.service.cartItem;

import com.restaurantApp.restaurantBack.dto.CartItemDTO;
import com.restaurantApp.restaurantBack.entity.CartItem;

import java.util.List;

public interface CartItemService {


    CartItemDTO addCartItem(CartItemDTO cartItemDTO,int customerId);

    CartItemDTO findById(int cartItemId);

    List<CartItemDTO> findAllCartItemsByCustomerId(int customerId);

    CartItemDTO updateCartItem(CartItemDTO cartItemDTO,int customerId);

    void deleteById(int cartItemId);

}
