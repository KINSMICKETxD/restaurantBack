package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Integer> {


    Optional<User> findByUserName(String username);
}
