package com.restaurantApp.restaurantBack.dao.CustomerDAO;

import com.restaurantApp.restaurantBack.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressDAO extends JpaRepository<CustomerAddress,Integer> {
}
