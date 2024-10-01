package com.restaurantApp.restaurantBack.controller;

import com.restaurantApp.restaurantBack.dto.ReservationDTO;
import com.restaurantApp.restaurantBack.dto.ReservationDurationDTO;
import com.restaurantApp.restaurantBack.entity.Reservation;
import com.restaurantApp.restaurantBack.service.reservation.ReservationService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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

    @GetMapping("/table/{tableId}")
    public List<ReservationDurationDTO> getAllReservationForTable(@PathVariable(name = "tableId") int tableId){
        return this.reservationService.getAllReservationByTableId(tableId);
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<ReservationDTO> createReservation(@Valid @RequestBody ReservationDTO reservationDTO,@PathVariable("customerId")int customerId){

        ReservationDTO createdReservation = this.reservationService.createNewReservation(reservationDTO,customerId);

        return ResponseEntity.ok(createdReservation);
    }
    @GetMapping("/{customerId}/all")
    public ResponseEntity<List<ReservationDTO>> getAllReservationsForCustomer(@PathVariable("customerId")int customerId){
        List<ReservationDTO> reservationDTOS = this.reservationService.findAllByCustomerId(customerId);

        return ResponseEntity.ok(reservationDTOS);
    }
    @PutMapping("/{reservationId}")
    public ResponseEntity<ReservationDTO> updateReservation(@RequestBody ReservationDTO reservationDTO,int reservationId){

        ReservationDTO updatedReservation = this.reservationService.updateReservation(reservationDTO,reservationId);

        return ResponseEntity.ok(updatedReservation);
    }
    @PutMapping("/{reservationId}/cancel")
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable("reservationId")int reservationId){

        return ResponseEntity.ok(this.reservationService.cancelReservation(reservationId));

    }






}
