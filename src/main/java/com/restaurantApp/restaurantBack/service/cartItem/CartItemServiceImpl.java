package com.restaurantApp.restaurantBack.service.cartItem;

import com.restaurantApp.restaurantBack.dao.CartDAO;
import com.restaurantApp.restaurantBack.dao.CartItemDAO;
import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerDAO;
import com.restaurantApp.restaurantBack.dao.MenuItemDAO;
import com.restaurantApp.restaurantBack.dto.CartItemDTOS.RequestCartItemDTO;
import com.restaurantApp.restaurantBack.dto.CartItemDTOS.ResponseCartItemDTO;
import com.restaurantApp.restaurantBack.dto.CartItemDTOS.UpdateCartItemDTO;
import com.restaurantApp.restaurantBack.entity.Cart;
import com.restaurantApp.restaurantBack.entity.CartItem;
import com.restaurantApp.restaurantBack.entity.Customer;
import com.restaurantApp.restaurantBack.entity.MenuItem;
import com.restaurantApp.restaurantBack.exception.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartItemServiceImpl implements CartItemService{

    private CartItemDAO cartItemDAO;

    private CartDAO cartDAO;

    private MenuItemDAO menuItemDAO;

    private CustomerDAO customerDAO;

    @Autowired
    public CartItemServiceImpl(CartItemDAO cartItemDAO,CartDAO cartDAO,
                               MenuItemDAO menuItemDAO,CustomerDAO customerDAO){
        this.cartItemDAO = cartItemDAO;
        this.cartDAO = cartDAO;
        this.menuItemDAO = menuItemDAO;
        this.customerDAO = customerDAO;
    }




    @Override
    public ResponseCartItemDTO addCartItem(RequestCartItemDTO requestCartItemDTO,String userName) {
        Customer customer = this.customerDAO.findCustomerByUserName(userName).orElseThrow(
                ()->new CustomerNotFoundException("No Customer linked to user with username = "+userName)
        );
        Cart cart = this.cartDAO.findByCustomerId(customer.getId()).orElseThrow(
                ()->new CartNotFoundException("Customer with id "+customer.getId()+" doesn't have Cart.")
        );



        CartItem cartItem = toCartItemEntity(requestCartItemDTO);

        cart.addCartItem(cartItem);
        CartItem savedCartItem = this.cartItemDAO.save(cartItem);

        return toCartItemDTO(savedCartItem);

    }

    @Override
    public ResponseCartItemDTO findById(int cartItemId) {
        CartItem cartItem= this.cartItemDAO.findById(cartItemId).orElseThrow(
                ()->new CartItemNotFoundException("Item cart wit id = "+cartItemId+" is not found.")
        );
        return toCartItemDTO(cartItem);
    }

    @Override
    public ResponseCartItemDTO updateCartItem(UpdateCartItemDTO updateCartItemDTO, int cartItemId) {

        CartItem cartItem = this.cartItemDAO.findById(cartItemId).orElseThrow(
                ()-> new CartItemNotFoundException("Cart Item with id = "+cartItemId+" is not found.")
        );

        cartItem.setCustomization(updateCartItemDTO.getCustomization());
        cartItem.setQuantity(updateCartItemDTO.getQuantity());
        cartItem.setTotalPrice(Double.parseDouble(cartItem.getMenuItem().getPrice())*updateCartItemDTO.getQuantity());

        CartItem updatedCartItem = this.cartItemDAO.save(cartItem);

        return toCartItemDTO(updatedCartItem);

    }

    @Override
    public void deleteById(int cartItemId) {
        CartItem cartItem = this.cartItemDAO.findById(cartItemId).orElseThrow(
                ()-> new CartItemNotFoundException("Cart Item with ID = "+cartItemId+" is not found.")
        );
        this.cartItemDAO.delete(cartItem);
    }

    private Cart createNewCartForCustomer(Customer customer){

        Cart cart = new Cart();
        cart.setCreatedAt(LocalDateTime.now());
        cart.setCustomer(customer);
        cart.setActive(true);
        cart.setTotalAmount(0.0);
        return cartDAO.save(cart);
    }

    public ResponseCartItemDTO toCartItemDTO(CartItem cartItem){
        ResponseCartItemDTO responseCartItemDTO = new ResponseCartItemDTO();

       responseCartItemDTO.setCartItemId(cartItem.getId());
       responseCartItemDTO.setMenuItemName(cartItem.getMenuItem().getName());
       responseCartItemDTO.setCartId(cartItem.getCart().getId());
       responseCartItemDTO.setQuantity(cartItem.getQuantity());
       responseCartItemDTO.setTotalPrice(cartItem.getTotalPrice());
       responseCartItemDTO.setCustomization(cartItem.getCustomization());

        return responseCartItemDTO;
    }

    private CartItem toCartItemEntity(RequestCartItemDTO requestCartItemDTO) {
        CartItem cartItem = new CartItem();

        MenuItem menuItem = this.menuItemDAO.findById(requestCartItemDTO.getMenuItemId()).orElseThrow(
                () -> new MenuItemNotFoundException("MenuItem with id = " + requestCartItemDTO.getMenuItemId() + " is not found.")
        );

        cartItem.setMenuItem(menuItem);

        cartItem.setQuantity(requestCartItemDTO.getQuantity());


        double totalPrice = requestCartItemDTO.getQuantity()*Double.parseDouble(menuItem.getPrice());
        cartItem.setTotalPrice(totalPrice);

        cartItem.setCustomization(requestCartItemDTO.getCustomization());

        return cartItem;
    }
}
