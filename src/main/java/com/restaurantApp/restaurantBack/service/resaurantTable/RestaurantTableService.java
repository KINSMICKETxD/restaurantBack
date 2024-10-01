package com.restaurantApp.restaurantBack.service.resaurantTable;

import com.restaurantApp.restaurantBack.dto.ReservationDTO;
import com.restaurantApp.restaurantBack.dto.RestaurantTableDTO;
import com.restaurantApp.restaurantBack.entity.RestaurantTable;

import java.util.List;

public interface RestaurantTableService {

    RestaurantTable findTableById(int tableId);

    List<RestaurantTableDTO> findAll();

    void save(RestaurantTable table);

    void deleteTableById(int tableId);

    List<RestaurantTableDTO> getAllAvailableTable(ReservationDTO reservationDTO);


}
