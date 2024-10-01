package com.restaurantApp.restaurantBack.service.customer.CustomerAddress;

import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerAddressDAO;
import com.restaurantApp.restaurantBack.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerAddressServiceImpl implements CustomerAddressService{

    private CustomerAddressDAO addressDAO;


    @Autowired
    public CustomerAddressServiceImpl(CustomerAddressDAO addressDAO){
        this.addressDAO = addressDAO;
    }

    @Override
    public Address findCustomerAddressById(int addressId) {
        return addressDAO.findById(addressId).get();
    }

    @Override
    public List<Address> findAll() {
        return addressDAO.findAll();
    }

    @Override
    public void save(Address address) {

        addressDAO.save(address);

    }

    @Override
    public void deleteCustomerAddressById(int addressId) {

        addressDAO.deleteById(addressId);
    }
}
