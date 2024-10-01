package com.restaurantApp.restaurantBack;

import com.restaurantApp.restaurantBack.entity.Address;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class RestaurantBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantBackApplication.class, args);
	}

}
