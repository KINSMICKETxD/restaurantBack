package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDAO extends JpaRepository<Role,Integer> {

    Optional<Role> findByAuthority(String authority);
}
