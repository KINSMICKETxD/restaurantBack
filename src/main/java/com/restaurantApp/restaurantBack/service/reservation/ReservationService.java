package com.restaurantApp.restaurantBack.service.reservation;

import com.restaurantApp.restaurantBack.dto.ReservationDTO;
import com.restaurantApp.restaurantBack.dto.ReservationDurationDTO;
import com.restaurantApp.restaurantBack.entity.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation findById(int reservationId);

    Reservation findByIdAndCustomerId(int reservationId,int customerId);

    List<Reservation> findAllByCustomerId(int CustomerId);

    ReservationDTO save(ReservationDTO reservationDTO);

    void deleteById(int reservationId);

    List<ReservationDurationDTO> getAllReservationByTableId(int tableId);

}
