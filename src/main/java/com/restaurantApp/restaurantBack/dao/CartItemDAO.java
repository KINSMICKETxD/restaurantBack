package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemDAO extends JpaRepository<CartItem,Integer> {
}
