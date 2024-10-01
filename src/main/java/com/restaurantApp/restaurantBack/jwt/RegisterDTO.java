package com.restaurantApp.restaurantBack.jwt;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {


    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;
}
