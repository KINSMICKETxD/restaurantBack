package com.restaurantApp.restaurantBack.service.cart;

import com.restaurantApp.restaurantBack.dto.CartDTO;

public interface CartService {

    CartDTO getCartByUserName(String userName);


    String deleteCartByUserName(String userName);
}
