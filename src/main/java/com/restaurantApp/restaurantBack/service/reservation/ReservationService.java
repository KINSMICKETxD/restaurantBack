package com.restaurantApp.restaurantBack.service.reservation;

import com.restaurantApp.restaurantBack.entity.Reservation;

import java.util.List;

public interface ReservationService {

    Reservation findById(int reservationId);

    Reservation findByIdAndCustomerId(int reservationId,int customerId);

    List<Reservation> findAllByCustomerId(int CustomerId);

    void save(int customerId,Reservation reservation);

    void deleteById(int reservationId);
}
