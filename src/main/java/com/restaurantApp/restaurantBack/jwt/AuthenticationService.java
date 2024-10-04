package com.restaurantApp.restaurantBack.jwt;


import com.restaurantApp.restaurantBack.dao.RoleDAO;
import com.restaurantApp.restaurantBack.dao.UserDAO;
import com.restaurantApp.restaurantBack.entity.Role;
import com.restaurantApp.restaurantBack.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDAO userDAO;

    private final RoleDAO roleDAO;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public User singup(RegisterDTO registerDTO){
        User user   = new User();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
         user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setActive(true);

        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.findByAuthority("ROLE_USER").get());
        user.setAuthorities(roles);
        User savedUser = this.userDAO.save(user);

        return savedUser;
    }

    public User singIn(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),loginDTO.getPassword()
                )
        );

        return userDAO.findByEmail(loginDTO.getEmail()).orElseThrow(
                ()->new UsernameNotFoundException("User cannot be found.")
        );
    }
}
