package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableDAO extends JpaRepository<RestaurantTable,Integer> {
}
