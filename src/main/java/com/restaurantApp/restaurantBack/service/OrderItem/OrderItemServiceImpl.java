package com.restaurantApp.restaurantBack.service.OrderItem;

import com.restaurantApp.restaurantBack.dto.OrderItemDTO;
import com.restaurantApp.restaurantBack.entity.OrderItem;
import com.restaurantApp.restaurantBack.service.Order.OrderService;

public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemService orderItemService;

    public OrderItemServiceImpl(OrderItemService orderItemService){

        this.orderItemService = orderItemService;
    }

    public static OrderItemDTO toOrderItemDTO(OrderItem orderItem){
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setMenuItemId(orderItem.getMenuItem().getId());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setPrice(orderItem.getPrice());
        return orderItemDTO;
    }
}
