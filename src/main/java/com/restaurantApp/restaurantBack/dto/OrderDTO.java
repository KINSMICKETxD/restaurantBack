package com.restaurantApp.restaurantBack.dto;

import com.restaurantApp.restaurantBack.Validation.OrderStatusValidation.OrderStatusValidation;
import com.restaurantApp.restaurantBack.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {


    private int orderId;


    @NotNull(message = "order number cannot be NULL.")
    private String orderNumber;

    @NotNull(message = "order date cannot be NULL.")
    private LocalDateTime orderDate;

    @NotNull(message = "Order status cannot be NULL.")

    @OrderStatusValidation(enumClass = OrderStatus.class)
    private OrderStatus status;

    private Double totalPrice;

    private Integer customerId;

    private String customerFullName;

    private List<OrderItemDTO> orderItemDTOS;
}
