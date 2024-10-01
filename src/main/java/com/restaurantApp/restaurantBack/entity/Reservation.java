package com.restaurantApp.restaurantBack.entity;

import com.restaurantApp.restaurantBack.Validation.ValidReservationDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidReservationDate
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private RestaurantTable table;


    @Column(name = "reservation_date_begin")
    @FutureOrPresent(message = "reservation date  can't be in the past")
    private LocalDateTime reservationDateTimeBegin;

    @Column(name = "reservation_date_end")
    @FutureOrPresent(message = "reservation Date should cannot be in the Past")
    private LocalDateTime reservationDateTimeEnd;

    @Column(name = "guests_number")
    private int guestNumber;

    @Column(name = "special_Instructors")
    private String specialInstructions;

    @Column(name = "status")
    @Pattern(regexp = "ACTIVE|CANCELED|active|canceled",message = "status must be either ACTIVE or CANCELED.")
    private String status;








}
