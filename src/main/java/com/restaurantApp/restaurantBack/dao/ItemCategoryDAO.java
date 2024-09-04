package com.restaurantApp.restaurantBack.dao;

import com.restaurantApp.restaurantBack.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ItemCategoryDAO extends JpaRepository<ItemCategory,Integer> {

    Optional<ItemCategory> findByName(String categoryName);
}
