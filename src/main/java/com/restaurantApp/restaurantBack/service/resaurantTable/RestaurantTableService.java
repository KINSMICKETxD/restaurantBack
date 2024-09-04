package com.restaurantApp.restaurantBack.service.resaurantTable;

import com.restaurantApp.restaurantBack.entity.RestaurantTable;

import java.util.List;

public interface RestaurantTableService {

    RestaurantTable findTableById(int tableId);

    List<RestaurantTable> findAll();

    void save(RestaurantTable table);

    void deleteTableById(int tableId);


}
