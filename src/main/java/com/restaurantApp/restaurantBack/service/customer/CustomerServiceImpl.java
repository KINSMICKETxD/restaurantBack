package com.restaurantApp.restaurantBack.service.customer;

import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerDAO;
import com.restaurantApp.restaurantBack.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

   private CustomerDAO customerDAO;


   @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer findCustomerById(int customerId) {

       Customer customer = customerDAO.findById(customerId).get();


       return customer;
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public Customer save(Customer theCustomer) {
        return customerDAO.save(theCustomer);
    }

    @Override
    public boolean deleteCustomerById(int customerId) {

       Customer theCustomer = customerDAO.findById(customerId).get();
       if(theCustomer == null){
           return false;
       }else{
           customerDAO.deleteById(customerId);
           return true;
       }
    }
}
