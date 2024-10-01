package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.Cart;
import com.restaurantApp.restaurantBack.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CartDAO extends JpaRepository<Cart,Integer> {

    Optional<Cart> findByCustomerId(int customerId);

    @Query("from CartItem c where c.cart.id = :cartId")
    Optional<List<CartItem>> getAllCartItemsByCartId(@Param("cartId")int cartId);

}
