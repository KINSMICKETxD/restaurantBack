package com.restaurantApp.restaurantBack.controller;


import com.restaurantApp.restaurantBack.dto.OrderDTO;
import com.restaurantApp.restaurantBack.entity.Order;
import com.restaurantApp.restaurantBack.entity.User;
import com.restaurantApp.restaurantBack.service.Order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private OrderService orderService;



    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

 /*   @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(@AuthenticationPrincipal UserDetails userDetails){


       OrderDTO newOrder = this.orderService.placeOrder(userDetails.getUsername());

        return ResponseEntity.ok("yaaay");
    }*/


}
