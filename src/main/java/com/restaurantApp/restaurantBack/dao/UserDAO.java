package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
}
