package com.restaurantApp.restaurantBack.DaoTest;


import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerAddressDAO;
import com.restaurantApp.restaurantBack.entity.Address;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerAddressDAOTest {


    private Address address;

    @Autowired
    private CustomerAddressDAO customerAddressDAO;

    @BeforeEach
    public void setUp(){
        address = Address.builder().streetAddress("streetAddressTest")
                .city("TestCity")
                .state("stateTest")
                .postalCode("postalCodeTest")
                .country("countryTest")
                .build();
    }

    @Test
    public void whenSavingNewAddress_success(){

        Address savedAddress = this.customerAddressDAO.save(address);

        Assertions.assertThat(savedAddress.getId()).isGreaterThan(0);
    }

    @Test
    public void whenSavingAddress_success(){

        Address address1 = Address.builder().streetAddress("streetAddressTest2")
                .city("TestCity2")
                .state("stateTest2")
                .postalCode("postalCodeTest2")
                .country("countryTest2")
                .build();
        this.customerAddressDAO.save(address);
        this.customerAddressDAO.save(address1);

        List<Address> addressList = this.customerAddressDAO.findAll();

        Assertions.assertThat(addressList).isNotNull();
        Assertions.assertThat(addressList.size()).isEqualTo(2);

    }

    @Test
    public void whenUpdateAddress_sucess(){

        Address savedAddress = this.customerAddressDAO.save(address);
        savedAddress.setState("updatedState");

        Address updatedAddress = this.customerAddressDAO.save(savedAddress);

        Assertions.assertThat(updatedAddress).isNotNull();
        Assertions.assertThat(updatedAddress.getState()).isEqualTo(savedAddress.getState());
    }

    @Test
    public void whenDeleteEntity_success(){

        Address savedAddress = this.customerAddressDAO.save(address);
        this.customerAddressDAO.deleteById(savedAddress.getId());

        Assertions.assertThat(this.customerAddressDAO.findById(savedAddress.getId())).isEmpty();
    }

    @Test
    public void whenDeleteNonExistedEntity_failed(){

        Assertions.assertThatCode(()->this.customerAddressDAO.deleteById(0)).doesNotThrowAnyException();
    }

    @Test
    public void whenGetAddressById_success(){

        Address savedAddress = this.customerAddressDAO.save(address);

        Assertions.assertThat(this.customerAddressDAO.findById(savedAddress.getId())).isNotEmpty();
    }


}
