package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CartDAO extends JpaRepository<Cart,Integer> {

    Optional<Cart> findByCustomerId(int customerId);
}
