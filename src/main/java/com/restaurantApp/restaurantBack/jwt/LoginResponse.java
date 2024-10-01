package com.restaurantApp.restaurantBack.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginResponse {

    private String token;

    private long ExpiresIn;
}
