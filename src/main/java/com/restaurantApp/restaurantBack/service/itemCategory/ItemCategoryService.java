package com.restaurantApp.restaurantBack.service.itemCategory;

import com.restaurantApp.restaurantBack.entity.ItemCategory;
import com.restaurantApp.restaurantBack.entity.MenuItem;

import java.util.List;

public interface ItemCategoryService {

    ItemCategory findItemCategoryById(int itemCategoryId);

    ItemCategory findItemCategoryByName(String categoryName);

    List<ItemCategory> findAll();

    ItemCategory save(ItemCategory itemCategory);

    ItemCategory updateCategory(int categoryId,ItemCategory itemCategory);

    void deleteMenuItemById(int itemCategoryId);
}
