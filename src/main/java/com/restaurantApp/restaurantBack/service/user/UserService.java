package com.restaurantApp.restaurantBack.service.user;

import com.restaurantApp.restaurantBack.entity.User;
import com.restaurantApp.restaurantBack.jwt.LoginResponse;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
}
