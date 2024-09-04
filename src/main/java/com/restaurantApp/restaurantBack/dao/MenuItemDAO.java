package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MenuItemDAO extends JpaRepository<MenuItem,Integer> {
}
