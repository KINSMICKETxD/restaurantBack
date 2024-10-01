package com.restaurantApp.restaurantBack.service.reservation;

import com.restaurantApp.restaurantBack.dto.ReservationDTO;
import com.restaurantApp.restaurantBack.dto.ReservationDurationDTO;
import com.restaurantApp.restaurantBack.entity.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation findById(int reservationId);

    Reservation findByIdAndCustomerId(int reservationId,int customerId);

    List<ReservationDTO> findAllByCustomerId(int CustomerId);

    ReservationDTO updateReservation(ReservationDTO reservationDTO,int reservationId);

    void deleteById(int reservationId);

    List<ReservationDurationDTO> getAllReservationByTableId(int tableId);

    ReservationDTO createNewReservation(ReservationDTO reservationDTO,int customerId);

    ReservationDTO cancelReservation(int reservationId);

}
