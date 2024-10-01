package com.restaurantApp.restaurantBack.controller;


import com.restaurantApp.restaurantBack.dto.ReservationDTO;
import com.restaurantApp.restaurantBack.dto.RestaurantTableDTO;
import com.restaurantApp.restaurantBack.service.resaurantTable.RestaurantTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class RestaurantTableController {

    private RestaurantTableService tableService;


    @Autowired
    public RestaurantTableController(RestaurantTableService tableService){
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantTableDTO>> getAllTables(){

        return ResponseEntity.ok(this.tableService.findAll());
    }

    @GetMapping("/available")
    public ResponseEntity<List<RestaurantTableDTO>> getAllAvailableTables(@RequestBody ReservationDTO reservationDTO){

        List<RestaurantTableDTO> restaurantTableDTOS = this.tableService.getAllAvailableTable(reservationDTO);


        return ResponseEntity.ok(restaurantTableDTOS);
    }
}

