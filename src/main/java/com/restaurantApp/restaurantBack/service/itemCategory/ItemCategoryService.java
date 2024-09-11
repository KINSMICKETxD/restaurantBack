package com.restaurantApp.restaurantBack.service.itemCategory;

import com.restaurantApp.restaurantBack.dto.ItemCategoryDTO;
import com.restaurantApp.restaurantBack.dto.MenuItemDTO;
import com.restaurantApp.restaurantBack.entity.ItemCategory;
import com.restaurantApp.restaurantBack.entity.MenuItem;

import java.util.List;

public interface ItemCategoryService {

    ItemCategoryDTO findItemCategoryById(int itemCategoryId);

    ItemCategory findItemCategoryByName(String categoryName);

    List<ItemCategory> findAll();

    ItemCategory save(ItemCategory itemCategory);

    ItemCategory updateCategory(int categoryId,ItemCategory itemCategory);

    void deleteMenuItemById(int itemCategoryId);

    List<MenuItemDTO> getAllMenuItemsByCategoryId(int categoryId);

}
