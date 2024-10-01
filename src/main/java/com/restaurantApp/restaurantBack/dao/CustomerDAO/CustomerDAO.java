package com.restaurantApp.restaurantBack.dao.CustomerDAO;


import com.restaurantApp.restaurantBack.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDAO extends JpaRepository<Customer,Integer> {


    @Query("from Customer c where c.user.userName = :userName")
    Optional<Customer> findCustomerByUserName(@Param("userName")String userName);

    @Query("select c.id from Customer c where c.user.userName = :userName")
    Optional<Integer> findCustomerIdByUserName(@Param("userName")String userName);


}
