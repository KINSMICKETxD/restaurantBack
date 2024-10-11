package com.restaurantApp.restaurantBack.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "restaurant_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "table_number")
    private int tableNumber;

    @Column(name = "description")
    private String desc;

    @Column(name = "seating_capacity")
    private int seatingCapacity;


    @Column(name = "availability")
    private boolean isAvailable;


    @OneToMany(mappedBy = "table",fetch = FetchType.LAZY)
    private Set<Reservation> reservations;


}
