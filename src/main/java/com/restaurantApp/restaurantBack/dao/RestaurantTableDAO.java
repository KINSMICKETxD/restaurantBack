package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantTableDAO extends JpaRepository<RestaurantTable,Integer> {


    Optional<List<RestaurantTable>> findAllByOrderByTableNumberAsc();

    @Query("from RestaurantTable t where t.seatingCapacity >= :numberOfGuests " +
            "and t.id NOT IN (select r.table.id from Reservation r where " +
            "r.reservationDateTimeBegin < :endDateTime " +
            "and r.reservationDateTimeEnd > :beginDateTime)")
    Optional<List<RestaurantTable>> findTableForCustomer(@Param("numberOfGuests")int numberOfGuests,
                                                         @Param("endDateTime")LocalDateTime endDateTime,
                                                         @Param("beginDateTime")LocalDateTime beginDateTime);

}
