package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationDAO extends JpaRepository<Reservation,Integer> {


    Optional<List<Reservation>> findByCustomerId(int customerId);

    Optional<Reservation> findByIdAndCustomerId(int reservationId,int customerId);


    @Query("select COUNT(r) from Reservation r where r.table.id = :tableId and r.customer.id <> :customerId and " +
            "r.reservationDateTimeBegin < :reservationDateTimeEnd and r.reservationDateTimeEnd > :reservationDateTimeBegin")
    public Integer isTableAvailable(@Param("tableId")int tableId,@Param("reservationDateTimeBegin")LocalDateTime timeBegin,
                                    @Param("reservationDateTimeEnd")LocalDateTime timeEnd,@Param("customerId")int customerId);


    @Query("from Reservation r where " +
            "r.table.id = :tableId and r.reservationDateTimeBegin > now() ")
    public List<Reservation>  getAllReservationsForATable(@Param("tableId")int tableId);


    @Query("select count(r.id) from Reservation r where r.customer.id = :customerId and " +
            "r.reservationDateTimeBegin >= :dateTimeNow")
    public Optional<Integer> getAllActiveReservationsForCustomer(@Param("customerId")int customerId,
                                                                   @Param("dateTimeNow")LocalDateTime dateTime);




}
