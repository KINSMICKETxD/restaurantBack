package com.restaurantApp.restaurantBack.controller;


import com.restaurantApp.restaurantBack.entity.Customer;
import com.restaurantApp.restaurantBack.service.customer.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    private CustomerService customerService;


    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){

        List<Customer> customers = customerService.findAll();

        return ResponseEntity.ok(customers);
    }
    @GetMapping("/{customerId}")
    public Customer getACustomerById(@PathVariable int customerId){

        Customer theCustomer = this.customerService.findCustomerById(customerId);

        return theCustomer;
    }
    @PostMapping
    public Customer createNewCustomer(@RequestBody Customer newCustomer){

        Customer createdCustomer = this.customerService.save(newCustomer);

        return createdCustomer;
    }
    @PutMapping("/{customerId}")
    public Customer updateCustomer(@RequestBody Customer theCustomer){

        Customer updatedCustomer =  this.customerService.save(theCustomer);

        return updatedCustomer;
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable int customerId){

       boolean isDeleted = customerService.deleteCustomerById(customerId);
       if(isDeleted){
           return ResponseEntity.noContent().build();
       }else{
           return ResponseEntity.notFound().build();
       }
    }
}
