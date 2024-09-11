package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.dto.MenuItemDTO;
import com.restaurantApp.restaurantBack.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MenuItemDAO extends JpaRepository<MenuItem,Integer> {

    Optional<List<MenuItem>> getByCategoryId(int categoryId);
}
