package com.restaurantApp.restaurantBack.service.user;


import com.restaurantApp.restaurantBack.dao.UserDAO;
import com.restaurantApp.restaurantBack.entity.User;
import com.restaurantApp.restaurantBack.exception.UserNotFoundException;
import com.restaurantApp.restaurantBack.jwt.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @Override
    public User getUserById(int id) {
        return this.userDAO.findById(id).orElseThrow(
                ()-> new UserNotFoundException("User cannot be found with id ="+id)
        );
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public void deleteUser(int id) {

        User user = this.userDAO.findById(id).orElseThrow(
                ()->new UserNotFoundException("User cannot be found with id = "+id)
        );
        this.userDAO.delete(user);

    }


}
