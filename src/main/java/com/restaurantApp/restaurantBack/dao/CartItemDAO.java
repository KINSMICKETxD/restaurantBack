package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CartItemDAO extends JpaRepository<CartItem,Integer> {


    @Query("delete from CartItem c where c.cart.id = :cartId")
    void deleteAllCartItemsByCartId(@Param("cartId")int cartId);
}
