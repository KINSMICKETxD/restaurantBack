package com.restaurantApp.restaurantBack.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    @JsonManagedReference
    private List<CartItem> cartItemList;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_active")
    private boolean isActive;


    public void addCartItem(CartItem cartItem){
        if(cartItemList == null){
            cartItemList = new ArrayList<>();
        }
        cartItem.setCart(this);
        cartItemList.add(cartItem);

        this.totalAmount += cartItem.getTotalPrice();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer=" + customer.getId() +
                ", cartItemList=" + cartItemList +
                ", totalAmount=" + totalAmount +
                ", createdAt=" + createdAt +
                ", isActive=" + isActive +
                '}';
    }
}
