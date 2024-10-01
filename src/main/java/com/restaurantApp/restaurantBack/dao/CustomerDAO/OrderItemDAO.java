package com.restaurantApp.restaurantBack.dao.CustomerDAO;

import com.restaurantApp.restaurantBack.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDAO extends JpaRepository<OrderItem,Integer> {
}
