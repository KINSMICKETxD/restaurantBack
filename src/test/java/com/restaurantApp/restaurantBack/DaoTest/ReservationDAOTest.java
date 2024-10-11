package com.restaurantApp.restaurantBack.DaoTest;


import com.restaurantApp.restaurantBack.dao.CustomerDAO.CustomerDAO;
import com.restaurantApp.restaurantBack.dao.ReservationDAO;
import com.restaurantApp.restaurantBack.dao.RestaurantTableDAO;
import com.restaurantApp.restaurantBack.entity.Customer;
import com.restaurantApp.restaurantBack.entity.Reservation;
import com.restaurantApp.restaurantBack.entity.RestaurantTable;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.xml.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertThrows;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReservationDAOTest {

    @Autowired
    private ReservationDAO reservationDAO;

    @Autowired
    private RestaurantTableDAO restaurantTableDAO;

    @Autowired
    private CustomerDAO customerDAO;



    private Reservation reservation;

    private RestaurantTable restaurantTable;

    private Customer customer;



    @BeforeEach
    public void setUp(){

        restaurantTable = RestaurantTable.builder().tableNumber(1)
                .desc("testDesc1").
                seatingCapacity(4).
                isAvailable(true).build();
        this.restaurantTableDAO.save(restaurantTable);

        customer = Customer.builder().build();

        this.customerDAO.save(customer);

        reservation = Reservation.builder().customer(customer)
                .table(restaurantTable)
                .reservationDateTimeBegin(LocalDateTime.of(2024,10,13,18,0))
                .reservationDateTimeEnd(LocalDateTime.of(2024,10,13,18,0))
                .guestNumber(2)
                .specialInstructions("no Special Instructions")
                .status("active").build();
    }

    @Test
    public void testSaveReservation_success(){

        //Arrange


        //Act
        this.reservationDAO.save(reservation);

        //Assert
        Assertions.assertThat(reservation).isNotNull();
        Assertions.assertThat(reservation.getId()).isGreaterThan(0);
        Assertions.assertThat(reservation.getTable()).isNotNull();
        Assertions.assertThat(reservation.getCustomer()).isNotNull();
    }


    @Test
    @DisplayName("Test if saving Reservation with invalid Status value will failed")
    public void testSaveReservationWithInvalidStatusValue_failed(){

        //Arrange
        reservation.setStatus("invalidValue");

        //Act

        //Assert
        assertThrows(ConstraintViolationException.class,()->{
            this.reservationDAO.save(reservation);
        });

    }

    @Test
    @DisplayName("Test if saving Reservation with invalid numberOfGuest will failed")
    public void testSavingReservationWithInvalidNumberOfGuests_failed(){

        //Arrange
        reservation.setGuestNumber(0);

        //Act


        //Assert
        assertThrows(ConstraintViolationException.class,()->{
            this.reservationDAO.save(reservation);
        });

    }
    @Test
    public void testFindReservationById_success(){


        //Arrange
        this.reservationDAO.save(reservation);

        //Act
        Optional<Reservation> foundReservation = this.reservationDAO.findById(1);


        //Assert
        Assertions.assertThat(foundReservation).isPresent();
        Assertions.assertThat(foundReservation.get().getId()).isEqualTo(1);
    }

    @Test
    public void testFindReservationsByCustomerId_success(){

        //Arrange
        Reservation reservation1 = Reservation.builder().customer(customer)
                .table(restaurantTable)
                .reservationDateTimeBegin(LocalDateTime.of(2024,10,20,18,0))
                .reservationDateTimeEnd(LocalDateTime.of(2024,10,20,18,0))
                .guestNumber(2)
                .specialInstructions("no Special Instructions")
                .status("active").build();
        this.reservationDAO.save(reservation);
        this.reservationDAO.save(reservation1);

        //Act
        Optional<List<Reservation>> reservations = this.reservationDAO.findByCustomerId(1);

        //Assert
        Assertions.assertThat(reservations).isPresent();
        Assertions.assertThat(reservations.get().size()).isEqualTo(2);


    }

    @Test
    public void testFindAllReservationsForATable_success(){

        //Arrange

        Reservation reservation1 = Reservation.builder().customer(customer)
                .table(restaurantTable)
                .reservationDateTimeBegin(LocalDateTime.of(2024,10,20,18,0))
                .reservationDateTimeEnd(LocalDateTime.of(2024,10,20,18,0))
                .guestNumber(2)
                .specialInstructions("no Special Instructions")
                .status("active").build();
        this.reservationDAO.save(reservation);
        this.reservationDAO.save(reservation1);


        //Act

        Optional<List<Reservation>> reservations = this.reservationDAO.getAllActiveReservationsForATable(1);


        //Assert
        Assertions.assertThat(reservations).isPresent();
        Assertions.assertThat(reservations.get().size()).isEqualTo(2);

    }

    @Test
    @DisplayName("test updating Reservation Status")
    public void testUpdateStatus_success(){

        //Arrange
        this.reservationDAO.save(reservation);
        Reservation foundReservation = this.reservationDAO.findById(1).get();

        //Act
        foundReservation.setStatus("canceled");
        reservation = this.reservationDAO.save(foundReservation);

        //Assert
        Assertions.assertThat(reservation).isNotNull();
        Assertions.assertThat(reservation.getId()).isEqualTo(1);
        Assertions.assertThat(reservation.getStatus()).isEqualTo("canceled");
    }

    @Test
    @DisplayName("Test Delete a reservation")
    public void testDeleteById_success(){

        //Arrange
        this.reservationDAO.save(reservation);

        //Act
        this.reservationDAO.delete(reservation);

        Optional<Reservation> deletedReservation = this.reservationDAO.findById(reservation.getId());

        //Assert
        Assertions.assertThat(deletedReservation).isNotPresent();
    }




}
