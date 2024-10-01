package com.restaurantApp.restaurantBack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;



    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private Set<Order> orders;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", user=" + user +
                ", reservations=" + reservations +
                ", orders=" + orders +
                '}';
    }
}
