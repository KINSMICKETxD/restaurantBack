package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationDAO extends JpaRepository<Reservation,Integer> {


    List<Reservation> findByCustomerId(int customerId);

    Optional<Reservation> findByIdAndCustomerId(int reservationId,int customerId);
}
