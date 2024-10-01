package com.restaurantApp.restaurantBack.controller;

import com.restaurantApp.restaurantBack.dto.CartDTO;
import com.restaurantApp.restaurantBack.dto.CartItemDTOS.RequestCartItemDTO;
import com.restaurantApp.restaurantBack.entity.Customer;
import com.restaurantApp.restaurantBack.entity.User;
import com.restaurantApp.restaurantBack.service.cart.CartService;
import com.restaurantApp.restaurantBack.service.cartItem.CartItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;



    @Autowired
    public CartController(CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCartByCustomer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        CartDTO cartDTO = this.cartService.getCartByUserName(userName);
        cartDTO.add(linkTo(methodOn(CartController.class).getCartByCustomer()).withSelfRel());
        return ResponseEntity.ok(cartDTO);
    }

}
