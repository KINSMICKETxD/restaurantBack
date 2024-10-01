package com.restaurantApp.restaurantBack.service.Order;

import com.restaurantApp.restaurantBack.dto.OrderDTO;

public interface OrderService {


    OrderDTO placeOrder(String userName);
}
