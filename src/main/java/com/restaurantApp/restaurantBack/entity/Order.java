package com.restaurantApp.restaurantBack.entity;


import com.restaurantApp.restaurantBack.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "customer_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_number",updatable = false)
    private String orderNumber;

    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @Column(name = "total_price",updatable = false)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "customer_id",updatable = false)
    private Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    @Column(name = "order_date",updatable = false)
    private LocalDateTime orderDate;


    public void setOrderNumber(){
        this.orderNumber = UUID.randomUUID().toString();
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void deleteOrderItem(OrderItem orderItem){
        orderItem.setOrder(null);
        this.orderItems.remove(orderItem);
    }


    public void deleteAllOrderItems(){
        Iterator<OrderItem> iterator = this.orderItems.iterator();

        while(iterator.hasNext()){
            OrderItem orderItem = iterator.next();
            orderItem.setOrder(null);
            iterator.remove();
        }
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Order order = (Order) o;
        return getOrderId() != null && Objects.equals(getOrderId(), order.getOrderId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
