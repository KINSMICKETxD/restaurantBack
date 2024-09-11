package com.restaurantApp.restaurantBack.service.menuItem;

import com.restaurantApp.restaurantBack.dto.MenuItemDTO;
import com.restaurantApp.restaurantBack.entity.MenuItem;

import java.util.HashMap;
import java.util.List;

public interface MenuItemService {

     MenuItemDTO findById(int itemId);

    List<MenuItemDTO> findAll();

    MenuItem save(MenuItem menuItem);

    void deleteMenuItemById(int itemId);

    HashMap<String,List<MenuItemDTO>> getMenuItemsOrganizedByCategory();

}
