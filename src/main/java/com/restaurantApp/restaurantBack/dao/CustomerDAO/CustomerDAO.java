package com.restaurantApp.restaurantBack.dao.CustomerDAO;


import com.restaurantApp.restaurantBack.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<Customer,Integer> {



}
