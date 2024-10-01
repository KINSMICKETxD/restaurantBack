package com.restaurantApp.restaurantBack.service.cartItem;

import com.restaurantApp.restaurantBack.dto.CartItemDTOS.RequestCartItemDTO;
import com.restaurantApp.restaurantBack.dto.CartItemDTOS.ResponseCartItemDTO;
import com.restaurantApp.restaurantBack.dto.CartItemDTOS.UpdateCartItemDTO;
import org.apache.coyote.Response;

import java.util.List;

public interface CartItemService {


    ResponseCartItemDTO addCartItem(RequestCartItemDTO requestCartItemDTO,String userName);

    ResponseCartItemDTO findById(int cartItemId);

    ResponseCartItemDTO updateCartItem(UpdateCartItemDTO updateCartItemDTO, int cartItemId);

    void deleteById(int cartItemId);

}
