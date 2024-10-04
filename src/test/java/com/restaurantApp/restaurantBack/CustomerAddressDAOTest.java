package com.restaurantApp.restaurantBack;


import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerAddressDAO;
import com.restaurantApp.restaurantBack.entity.Address;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerAddressDAOTest {


    @Autowired
    private CustomerAddressDAO customerAddressDAO;

    @Test
    public void CustomerAddressDAO_saveAll_ReturnSavedAddresses(){

        //Arrange

        Address address1 = Address.builder().streetAddress("streetTest").city("testCity")
                .state("stateTest").postalCode("test123").country("testCountry").build();

        Address address2 = Address.builder().streetAddress("streetTest").city("testCity")
                .state("stateTest").postalCode("test123").country("testCountry").build();


        //Act
        customerAddressDAO.save(address1);
        customerAddressDAO.save(address2);

        List<Address> addressList = customerAddressDAO.findAll();

        //Assert
        Assertions.assertThat(addressList).isNotNull();
        Assertions.assertThat(addressList).hasSize(2);
    }
    @Test
    public void CustomerAddressDAO_findById_ReturnAddress(){

        Address address1 = Address.builder().streetAddress("streetTest").city("testCity")
                .state("stateTest").postalCode("test123").country("testCountry").build();

        this.customerAddressDAO.save(address1);

        Address address = this.customerAddressDAO.findById(address1.getId()).get();

        Assertions.assertThat(address).isNotNull();
    }

}
