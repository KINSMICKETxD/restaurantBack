package com.restaurantApp.restaurantBack.service.cartItem;

import com.restaurantApp.restaurantBack.dao.CartDAO;
import com.restaurantApp.restaurantBack.dao.CartItemDAO;
import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerDAO;
import com.restaurantApp.restaurantBack.dao.MenuItemDAO;
import com.restaurantApp.restaurantBack.dto.CartItemDTO;
import com.restaurantApp.restaurantBack.entity.Cart;
import com.restaurantApp.restaurantBack.entity.CartItem;
import com.restaurantApp.restaurantBack.entity.Customer;
import com.restaurantApp.restaurantBack.entity.MenuItem;
import com.restaurantApp.restaurantBack.exception.CartItemNotFoundException;
import com.restaurantApp.restaurantBack.exception.CustomerNotFoundException;
import com.restaurantApp.restaurantBack.exception.EmptyCardException;
import com.restaurantApp.restaurantBack.exception.ItemNotFoundException;
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

    public CartItemServiceImpl(CartItemDAO cartItemDAO,CartDAO cartDAO,
                               MenuItemDAO menuItemDAO,CustomerDAO customerDAO){
        this.cartItemDAO = cartItemDAO;
        this.cartDAO = cartDAO;
        this.menuItemDAO = menuItemDAO;
        this.customerDAO = customerDAO;
    }




    @Override
    public CartItemDTO addCartItem(CartItemDTO cartItemDTO,int customerId) {

        MenuItem menuItem = this.menuItemDAO.findById(cartItemDTO.getMenuItemId()).orElseThrow(
                ()-> new ItemNotFoundException("Menu Item with id = "+cartItemDTO.getMenuItemId()+" is not found.")
        );

        Customer customer = this.customerDAO.findById(customerId).orElseThrow(
                ()-> new CustomerNotFoundException("Customer with id = "+customerId+" is not found.")
        );

        Cart cart = this.cartDAO.findByCustomerId(customerId).orElseGet(
                ()-> createNewCartForCustomer(customer)
        );


        CartItem cartItem = new CartItem();

        cartItem.setMenuItem(menuItem);
        cartItem.setQuantity(cartItemDTO.getQuantity());
        double totalPrice = Double.parseDouble(menuItem.getPrice())*cartItemDTO.getQuantity();
        cartItem.setTotalPrice(totalPrice);
        cart.addCartItem(cartItem);

        cartItem.setCustomization(cartItemDTO.getCustomization());
        CartItem savedCartItem = this.cartItemDAO.save(cartItem);
        return convertEntityToDTO(savedCartItem);
    }

    @Override
    public CartItemDTO findById(int cartItemId) {
        CartItem cartItem = this.cartItemDAO.findById(cartItemId).get();
        return convertEntityToDTO(cartItem);
    }

    @Override
    public List<CartItemDTO> findAllCartItemsByCustomerId(int customerId) {

        Cart cart = this.cartDAO.findByCustomerId(customerId).orElseThrow(
                ()-> new CustomerNotFoundException("Cart not found for Customer with ID "+customerId)
        );

        List<CartItem> cartItems = cart.getCartItemList();
        if(cartItems.isEmpty()){
            throw new EmptyCardException("Card is Empty.");
        }
        List<CartItemDTO> cartItemDTOS = new ArrayList<>();
        for(CartItem m : cartItems){
            cartItemDTOS.add(convertEntityToDTO(m));
        }
        return cartItemDTOS;
    }

    @Override
    public CartItemDTO updateCartItem(CartItemDTO cartItemDTO, int customerId) {


        CartItem cartItem = convertDTOToEntity(cartItemDTO,customerId);
        return convertEntityToDTO(cartItemDAO.save(cartItem));

    }

    @Override
    public void deleteById(int cartItemId) {
        CartItem cartItem = this.cartItemDAO.findById(cartItemId).orElseThrow(
                ()-> new CartItemNotFoundException("Cart Item with ID = "+cartItemId+" is not found.")
        );
        this.cartItemDAO.delete(cartItem);
    }

    CartItemDTO convertEntityToDTO(CartItem cartItem){
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setMenuItemId(cartItem.getMenuItem().getId());
        cartItemDTO.setMenuItemName(cartItem.getMenuItem().getName());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setCustomization(cartItem.getCustomization());
        cartItemDTO.setCartItemId(cartItem.getId());
        return cartItemDTO;
    }

    CartItem convertDTOToEntity(CartItemDTO cartItemDTO,int customerId){

        CartItem cartItem = this.cartItemDAO.findById(cartItemDTO.getCartItemId()).orElseThrow(
                ()-> new CartItemNotFoundException("Cart Item with ID = "+cartItemDTO.getCartItemId()+" is not found")
        );
        cartItem.setId(cartItemDTO.getCartItemId());
        MenuItem menuItem = this.menuItemDAO.findById(cartItemDTO.getMenuItemId()).orElseThrow(
                ()-> new ItemNotFoundException("Menu Item with id = "+cartItemDTO.getMenuItemId()+" is not found.")
        );
        Cart cart = this.cartDAO.findByCustomerId(customerId).get();

        cartItem.setMenuItem(menuItem);
        cartItem.setCart(cart);

        cartItem.setQuantity(cartItemDTO.getQuantity());
        double totalPrice = Double.parseDouble(menuItem.getPrice()) * cartItemDTO.getQuantity();
        cartItem.setTotalPrice(totalPrice);
        cartItem.setCustomization(cartItemDTO.getCustomization());
        return cartItem;
    }

    private Cart createNewCartForCustomer(Customer customer){

        Cart cart = new Cart();
        cart.setCreatedAt(LocalDateTime.now());
        cart.setCustomer(customer);
        cart.setActive(true);
        cart.setTotalAmount(0.0);
        return cartDAO.save(cart);
    }
}
