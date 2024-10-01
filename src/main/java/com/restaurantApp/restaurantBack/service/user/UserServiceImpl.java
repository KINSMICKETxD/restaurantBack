package com.restaurantApp.restaurantBack.service.user;


import com.restaurantApp.restaurantBack.dao.UserDAO;
import com.restaurantApp.restaurantBack.entity.User;
import com.restaurantApp.restaurantBack.jwt.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserDAO userDAO;


    @Override
    public List<User> getAllUsers() {
        List<User> users = userDAO.findAll();

        return users;
    }


}
