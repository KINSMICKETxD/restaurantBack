package com.restaurantApp.restaurantBack.service.customer.CustomerAddress;

import com.restaurantApp.restaurantBack.entity.Address;

import java.util.List;

public interface CustomerAddressService {

    Address findCustomerAddressById(int addressId);


    List<Address> findAll();


    void save(Address address);


    void deleteCustomerAddressById(int addressId);


}
