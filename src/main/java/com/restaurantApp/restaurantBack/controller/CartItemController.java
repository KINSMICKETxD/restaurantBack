package com.restaurantApp.restaurantBack.controller;


import com.restaurantApp.restaurantBack.dto.CartItemDTOS.RequestCartItemDTO;
import com.restaurantApp.restaurantBack.dto.CartItemDTOS.ResponseCartItemDTO;
import com.restaurantApp.restaurantBack.dto.CartItemDTOS.UpdateCartItemDTO;
import com.restaurantApp.restaurantBack.entity.User;
import com.restaurantApp.restaurantBack.service.cartItem.CartItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart/cartItems")
public class CartItemController {


    private CartItemService cartItemService;


    @Autowired
    public CartItemController(CartItemService cartItemService){
        this.cartItemService = cartItemService;
    }

    @PostMapping()
    public ResponseEntity<ResponseCartItemDTO> addItemToCart(@Valid @RequestBody RequestCartItemDTO requestCartItemDTO){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        ResponseCartItemDTO responseCartItemDTO =  this.cartItemService.addCartItem(requestCartItemDTO, userName);

        return ResponseEntity.ok(responseCartItemDTO);
    }

    @GetMapping("/{cartItemId}")
    public ResponseEntity<ResponseCartItemDTO> findById(@PathVariable("cartItemId")int carteItemId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User authenticatedUser = (User)auth.getPrincipal();
        ResponseCartItemDTO responseCartItemDTO = this.cartItemService.findById(carteItemId);

        return ResponseEntity.ok(responseCartItemDTO);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<ResponseCartItemDTO> updateCartItem(@Valid @RequestBody UpdateCartItemDTO updateCartItemDTO, @PathVariable("cartItemId")int cartItemId){

        ResponseCartItemDTO responseCartItemDTO = this.cartItemService.updateCartItem(updateCartItemDTO,cartItemId);
        return ResponseEntity.ok(responseCartItemDTO);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<String> deleteById(@Valid @PathVariable("cartItemId")int cartItemId){

        this.cartItemService.deleteById(cartItemId);

        return ResponseEntity.ok("Cart Item deleted successfully.");
    }

}
