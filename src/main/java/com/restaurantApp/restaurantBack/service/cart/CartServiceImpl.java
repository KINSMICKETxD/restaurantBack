package com.restaurantApp.restaurantBack.service.cart;


import com.restaurantApp.restaurantBack.dao.CartDAO;
import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerDAO;
import com.restaurantApp.restaurantBack.dao.UserDAO;
import com.restaurantApp.restaurantBack.dto.CartDTO;
import com.restaurantApp.restaurantBack.dto.CartItemDTOS.ResponseCartItemDTO;
import com.restaurantApp.restaurantBack.entity.Cart;
import com.restaurantApp.restaurantBack.entity.CartItem;
import com.restaurantApp.restaurantBack.entity.Customer;
import com.restaurantApp.restaurantBack.exception.CartNotFoundException;
import com.restaurantApp.restaurantBack.exception.CustomerNotFoundException;
import com.restaurantApp.restaurantBack.exception.EmptyCardException;
import com.restaurantApp.restaurantBack.service.cartItem.CartItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    private CartDAO cartDAO;
    private CustomerDAO customerDAO;

    private CartItemServiceImpl cartItemService;
    private final UserDAO userDAO;


    @Autowired
    public CartServiceImpl(CartDAO cartDAO,CartItemServiceImpl cartItemService,CustomerDAO customerDAO,
                           UserDAO userDAO){
        this.cartDAO = cartDAO;
        this.cartItemService = cartItemService;
        this.customerDAO = customerDAO;
        this.userDAO = userDAO;
    }

    @Override
    public CartDTO getCartByUserName(String userName) {
        Integer customerId  = this.customerDAO.findCustomerIdByUserName(userName).orElseThrow(
                ()->new CustomerNotFoundException("No customer found for User with userName = "+userName)
        );

        Cart cart = this.cartDAO.findByCustomerId(customerId).orElseThrow(
                ()-> new CartNotFoundException("Customer with id = "+customerId+" does not have a cart.")
        );

        return toDTO(cart);


    }

    @Override
    public String deleteCartByUserName(String userName) {

        Integer customerId = this.customerDAO.findCustomerIdByUserName(userName).orElseThrow(
                ()->new CustomerNotFoundException("No Customer found with userId = "+userName)
        );

        Cart cart = this.cartDAO.findByCustomerId(customerId).orElseThrow(
                ()-> new CartNotFoundException("No cart found for Customer with id ="+customerId)
        );

        this.cartDAO.delete(cart);

        return "Cart Deleted Successfully.";
    }

    private CartDTO toDTO(Cart cart){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(cart.getId());
        List<CartItem> cartItems = cart.getCartItems();
        List<ResponseCartItemDTO> responseCartItemDTOS = new ArrayList<>();
        Double totalPrice = 0.0;

        for(CartItem c : cartItems){
            totalPrice +=c.getTotalPrice();
            responseCartItemDTOS.add(cartItemService.toCartItemDTO(c));
        }
        cartDTO.setCartItems(responseCartItemDTOS);

        cartDTO.setTotalPrice(String.format("%.2f",totalPrice));
        return cartDTO;
    }
}
