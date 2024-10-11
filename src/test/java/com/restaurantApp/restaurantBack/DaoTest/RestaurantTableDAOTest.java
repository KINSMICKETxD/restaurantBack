package com.restaurantApp.restaurantBack.DaoTest;


import com.restaurantApp.restaurantBack.dao.ReservationDAO;
import com.restaurantApp.restaurantBack.dao.RestaurantTableDAO;
import com.restaurantApp.restaurantBack.entity.Reservation;
import com.restaurantApp.restaurantBack.entity.RestaurantTable;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RestaurantTableDAOTest {


    @Autowired
    private RestaurantTableDAO restaurantTableDAO;

    @Autowired
    private ReservationDAO reservationDAO;

    private Reservation reservation;


    private RestaurantTable restaurantTable;


    @BeforeEach
    public void setUp(){
        restaurantTable = RestaurantTable.builder().tableNumber(1).desc("tableDescTest")
                .seatingCapacity(3).isAvailable(true).build();
    }

    @Test
    public void testFindByTableNumber_success(){

        //Arrange
        this.restaurantTableDAO.save(restaurantTable);

        //Act

        Optional<RestaurantTable> restaurantTable1 = this.restaurantTableDAO.findByTableNumber(1);


        //Assert
        Assertions.assertThat(restaurantTable1).isPresent();
        Assertions.assertThat(restaurantTable1.get().getTableNumber()).isEqualTo(1);
    }

    @Test
    public void testFindByTableNumber_failed(){

        //Arrange
        this.restaurantTableDAO.save(restaurantTable);


        //Act
        Optional<RestaurantTable> foundedRestaurantTable = this.restaurantTableDAO.findByTableNumber(4);


        //Assert
        Assertions.assertThat(foundedRestaurantTable).isNotPresent();
    }

    @Test
    public void testSaveTable_success(){

        //Act

        this.restaurantTableDAO.save(restaurantTable);

        //Assert
        Assertions.assertThat(restaurantTable).isNotNull();
        Assertions.assertThat(restaurantTable.getId()).isGreaterThan(0);

    }

    @Test
    public void testUpdateTable_success(){

        //Arrange
        this.restaurantTableDAO.save(restaurantTable);

        //Act
        RestaurantTable restaurantTable1 = this.restaurantTableDAO.findByTableNumber(1).get();
        restaurantTable1.setSeatingCapacity(5);
        this.restaurantTableDAO.save(restaurantTable1);
        RestaurantTable restaurantTable2 = this.restaurantTableDAO.findByTableNumber(1).get();

        //Assert
        Assertions.assertThat(restaurantTable2).isNotNull();
        Assertions.assertThat(restaurantTable2.getId()).isEqualTo(restaurantTable.getId());
        Assertions.assertThat(restaurantTable.getSeatingCapacity()).isEqualTo(5);
    }

    @Test
    public void testFindAllTables_success(){

        //Arrange

        RestaurantTable restaurantTable1 = RestaurantTable.builder().tableNumber(2).desc("tableDescTest2")
                .seatingCapacity(6).isAvailable(true).build();

        this.restaurantTableDAO.save(restaurantTable);
        this.restaurantTableDAO.save(restaurantTable1);

        //Act
        List<RestaurantTable> restaurantTables = this.restaurantTableDAO.findAll();

        //Assert
        Assertions.assertThat(restaurantTables).isNotNull();
        Assertions.assertThat(restaurantTables.size()).isEqualTo(2);
    }

    @Test
    public void testDeleteTable_success(){

        //Arrange
        this.restaurantTableDAO.save(restaurantTable);

        //Act
        this.restaurantTableDAO.deleteById(1);
        Optional<RestaurantTable> restaurantTable1 = this.restaurantTableDAO.findById(1);

        //Assert
        Assertions.assertThat(restaurantTable1).isNotPresent();
    }

    @Test
    public void testFindTableForCustomer_success(){

        //Arrange

            //create list of Tables:
        RestaurantTable restaurantTable1 = RestaurantTable.builder().tableNumber(2).desc("desc2")
                .seatingCapacity(5).isAvailable(true).build();

        RestaurantTable restaurantTable2 = RestaurantTable.builder().tableNumber(3).desc("desc3")
                .seatingCapacity(10).isAvailable(true).build();

        RestaurantTable restaurantTable3 = RestaurantTable.builder().tableNumber(4).desc("desc4")
                .seatingCapacity(12).isAvailable(false).build();

        this.restaurantTableDAO.save(restaurantTable);
        this.restaurantTableDAO.save(restaurantTable1);
        this.restaurantTableDAO.save(restaurantTable2);
        this.restaurantTableDAO.save(restaurantTable3);
            //Create List of reservations:

        reservation = Reservation.builder().table(restaurantTable)
                .reservationDateTimeBegin(LocalDateTime.of(2024,10,15,17,30))
                .reservationDateTimeEnd(LocalDateTime.of(2024,10,15,19,00))
                .specialInstructions("no special Instructions").status("active").build();

        this.reservationDAO.save(reservation);

        Reservation reservation1 = Reservation.builder().table(restaurantTable1)
                .reservationDateTimeBegin(LocalDateTime.of(2024,10,20,17,30))
                .reservationDateTimeEnd(LocalDateTime.of(2024,10,20,19,0))
                .specialInstructions("no special Instructions").status("active").build();
        this.reservationDAO.save(reservation1);

        Reservation reservation2 = Reservation.builder().table(restaurantTable2)
                .reservationDateTimeBegin(LocalDateTime.of(2024,10,29,17,30))
                .reservationDateTimeEnd(LocalDateTime.of(2024,10,29,19,0))
                .specialInstructions("no special Instructions").status("active").build();
        this.reservationDAO.save(reservation2);

        //Act
        List<RestaurantTable> restaurantTables = this.restaurantTableDAO.findTableForCustomer(
                2,LocalDateTime.of(2024,10,29,18,30),
                LocalDateTime.of(2024,10,29,19,0)).get();
        //Assert

        Assertions.assertThat(restaurantTables).isNotNull();
        Assertions.assertThat(restaurantTables.size()).isEqualTo(3);
        Assertions.assertThat(restaurantTables.contains(restaurantTable2)).isFalse();
    }

    @Test
    public void testFindTableForCustomer_failed(){

        //Arrange

        //create list of Tables:
        RestaurantTable restaurantTable1 = RestaurantTable.builder().tableNumber(2).desc("desc2")
                .seatingCapacity(5).isAvailable(true).build();

        RestaurantTable restaurantTable2 = RestaurantTable.builder().tableNumber(3).desc("desc3")
                .seatingCapacity(10).isAvailable(true).build();

        RestaurantTable restaurantTable3 = RestaurantTable.builder().tableNumber(4).desc("desc4")
                .seatingCapacity(12).isAvailable(false).build();

        this.restaurantTableDAO.save(restaurantTable);
        this.restaurantTableDAO.save(restaurantTable1);
        this.restaurantTableDAO.save(restaurantTable2);
        this.restaurantTableDAO.save(restaurantTable3);
        //Create List of reservations:

        reservation = Reservation.builder().table(restaurantTable)
                .reservationDateTimeBegin(LocalDateTime.of(2024,10,15,17,30))
                .reservationDateTimeEnd(LocalDateTime.of(2024,10,15,19,00))
                .specialInstructions("no special Instructions").status("active").build();

        this.reservationDAO.save(reservation);

        Reservation reservation1 = Reservation.builder().table(restaurantTable1)
                .reservationDateTimeBegin(LocalDateTime.of(2024,10,15,17,30))
                .reservationDateTimeEnd(LocalDateTime.of(2024,10,15,19,0))
                .specialInstructions("no special Instructions").status("active").build();
        this.reservationDAO.save(reservation1);

        Reservation reservation2 = Reservation.builder().table(restaurantTable2)
                .reservationDateTimeBegin(LocalDateTime.of(2024,10,15,17,30))
                .reservationDateTimeEnd(LocalDateTime.of(2024,10,15,19,0))
                .specialInstructions("no special Instructions").status("active").build();
        this.reservationDAO.save(reservation2);

        Reservation reservation3 = Reservation.builder().table(restaurantTable3)
                .reservationDateTimeBegin(LocalDateTime.of(2024,10,15,17,30))
                .reservationDateTimeEnd(LocalDateTime.of(2024,10,15,19,0))
                .specialInstructions("no special Instructions").status("active").build();
        this.reservationDAO.save(reservation3);

        //Act
        List<RestaurantTable> restaurantTables = this.restaurantTableDAO.findTableForCustomer(
                2,LocalDateTime.of(2024,10,15,18,30),
                LocalDateTime.of(2024,10,15,19,0)).get();

        //Assert

        Assertions.assertThat(restaurantTables).isNotNull();
        Assertions.assertThat(restaurantTables.size()).isEqualTo(0);
    }

}
