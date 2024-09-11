package com.restaurantApp.restaurantBack.controller;

import com.restaurantApp.restaurantBack.dto.ReservationDTO;
import com.restaurantApp.restaurantBack.dto.ReservationDurationDTO;
import com.restaurantApp.restaurantBack.entity.Reservation;
import com.restaurantApp.restaurantBack.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/{tableId}")
    public List<ReservationDurationDTO> getAllReservationForTable(@PathVariable(name = "tableId") int tableId){
        return this.reservationService.getAllReservationByTableId(tableId);
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationDTO reservationDTO){

        ReservationDTO newReservation = this.reservationService.save(reservationDTO);
        if(newReservation == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("The table is already reserved for the specified time range.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newReservation);
    }


}
