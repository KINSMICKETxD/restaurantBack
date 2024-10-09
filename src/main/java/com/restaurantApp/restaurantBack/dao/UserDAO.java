package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Integer> {


    Optional<User> findByEmail(String email);
}
