package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantTableDAO extends JpaRepository<RestaurantTable,Integer> {


    Optional<List<RestaurantTable>> findAllByOrderByTableNumberAsc();
}
