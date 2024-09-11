package com.restaurantApp.restaurantBack.controller;


import com.restaurantApp.restaurantBack.dto.CartItemDTO;
import com.restaurantApp.restaurantBack.entity.CartItem;
import com.restaurantApp.restaurantBack.service.cartItem.CartItemService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cartItems")
public class CartItemController {


    private CartItemService cartItemService;


    @Autowired
    public CartItemController(CartItemService cartItemService){
        this.cartItemService = cartItemService;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<CartItemDTO> addItemToCart(@Valid @RequestBody  CartItemDTO cartItemDTO, @PathVariable("customerId")int customerId){
        CartItemDTO savedCartItem =  this.cartItemService.addCartItem(cartItemDTO,customerId);

        return ResponseEntity.ok(savedCartItem);
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity<CartItemDTO> findById(@PathVariable("cartItemId")int carteItemId){
        return ResponseEntity.ok(cartItemService.findById(carteItemId));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CartItemDTO> updateCartItem(@Valid @RequestBody CartItemDTO cartItemDTO,
                                                      @PathVariable("customerId")int customerId){

        CartItemDTO updatedCartItemDTO = this.cartItemService.updateCartItem(cartItemDTO,customerId);
        return ResponseEntity.ok(updatedCartItemDTO);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<String> deleteById(@Valid @PathVariable("cartItemId")int cartItemId){

        this.cartItemService.deleteById(cartItemId);

        return ResponseEntity.ok("Cart Item deleted successfully.");
    }

}
