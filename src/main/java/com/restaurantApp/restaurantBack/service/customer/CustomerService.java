package com.restaurantApp.restaurantBack.service.customer;

import com.restaurantApp.restaurantBack.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer findCustomerById(int customerId);

    List<Customer> findAll();

    Customer save(Customer theCustomer);

    boolean deleteCustomerById(int customerId);




}
