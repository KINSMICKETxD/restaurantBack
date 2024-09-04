package com.restaurantApp.restaurantBack.service.customer.CustomerAddress;

import com.restaurantApp.restaurantBack.entity.CustomerAddress;

import java.util.List;

public interface CustomerAddressService {

    CustomerAddress findCustomerAddressById(int addressId);


    List<CustomerAddress> findAll();


    void save(CustomerAddress customerAddress);


    void deleteCustomerAddressById(int addressId);


}
