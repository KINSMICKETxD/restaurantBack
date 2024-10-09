package com.restaurantApp.restaurantBack.DaoTest;


import com.restaurantApp.restaurantBack.dao.UserDAO;
import com.restaurantApp.restaurantBack.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.text.html.Option;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserDAOTest {

    private User user;


    @Autowired
    private UserDAO userDAO;



    @BeforeEach
    public void setUp(){
        user = User.builder().firstName("testFirstName")
                .lastName("testLastName")
                .userName("testUserName")
                .email("testEmail")
                .password("testPassword")
                .isActive(true).build();

    }


    @Test
    public void testSaveUser_success(){

        User savedUser = this.userDAO.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
        Assertions.assertThat(savedUser.getEmail()).isEqualTo("testEmail");
    }

    @Test
    public void testFindUserById_success(){

        //Arrange
        this.userDAO.save(user);

        //Act
        Optional<User> foundUser = this.userDAO.findById(user.getId());

        //Assert

        Assertions.assertThat(foundUser).isPresent();
        Assertions.assertThat(foundUser.get().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserById_failed(){

        //Act
        Optional<User> foundUser = this.userDAO.findById(999);

        Assertions.assertThat(foundUser).isNotPresent();
    }

    @Test
    public void testFindByEmail_success(){


        //arrange
        this.userDAO.save(user);

        //act
        Optional<User> foundedUser = this.userDAO.findByEmail(user.getEmail());


        //assert
        Assertions.assertThat(foundedUser).isNotNull();
        Assertions.assertThat(foundedUser.get().getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindByEmail_failed(){

        //Arrange

        this.userDAO.save(user);

        //Act
        Optional<User> foundedUser = this.userDAO.findByEmail("nonExistanteEmail");


        //Assert
        Assertions.assertThat(foundedUser).isNotPresent();
    }

    @Test
    public void testUpdateUser_success(){

        //Arrange
        User savedUser = this.userDAO.save(user);
        savedUser.setEmail("updatedTestEmail");

        //Act
        User updatedUser = this.userDAO.save(savedUser);

        //Assert
        Assertions.assertThat(updatedUser).isNotNull();
        Assertions.assertThat(updatedUser.getEmail()).isEqualTo("updatedTestEmail");

    }

    @Test
    public void testDeleteById_success(){

        //Arrange
        this.userDAO.save(user);


        //Act
        this.userDAO.deleteById(user.getId());
        Optional<User> deletedUser = this.userDAO.findById(user.getId());


        //Assert
        Assertions.assertThat(deletedUser).isNotPresent();

    }

}
