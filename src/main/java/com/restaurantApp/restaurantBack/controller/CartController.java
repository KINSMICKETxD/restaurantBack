package com.restaurantApp.restaurantBack.controller;

import com.restaurantApp.restaurantBack.dto.CartItemDTO;
import com.restaurantApp.restaurantBack.service.cartItem.CartItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartItemService cartItemService;


    @Autowired
    public CartController(CartItemService cartItemService){
        this.cartItemService = cartItemService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CartItemDTO>> getAllCartItems(@PathVariable("customerId")int customerId){

        return ResponseEntity.ok(this.cartItemService.findAllCartItemsByCustomerId(customerId));

    }
    @PutMapping("/{customerId}")
    public ResponseEntity<CartItemDTO> updateCartItem(@RequestBody CartItemDTO cartItemDTO,@Valid Integer customerId){
        return null;
    }
}
