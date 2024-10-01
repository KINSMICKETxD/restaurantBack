package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order,Integer> {

}
