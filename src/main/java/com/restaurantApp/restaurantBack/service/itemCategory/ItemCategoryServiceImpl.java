package com.restaurantApp.restaurantBack.service.itemCategory;

import com.restaurantApp.restaurantBack.dao.ItemCategoryDAO;
import com.restaurantApp.restaurantBack.entity.ItemCategory;
import com.restaurantApp.restaurantBack.entity.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{

    private ItemCategoryDAO itemCategoryDAO;


    @Autowired
    public ItemCategoryServiceImpl(ItemCategoryDAO itemCategoryDAO){
        this.itemCategoryDAO = itemCategoryDAO;
    }


    @Override
    public ItemCategory findItemCategoryById(int itemId) {
        return itemCategoryDAO.findById(itemId).get();
    }

    @Override
    public ItemCategory findItemCategoryByName(String categoryName) {

        return itemCategoryDAO.findByName(categoryName).get();
    }

    @Override
    public List<ItemCategory> findAll() {
        return itemCategoryDAO.findAll();
    }

    @Override
    public ItemCategory save(ItemCategory itemCategory) {

        return itemCategoryDAO.save(itemCategory);
    }

    @Override
    public ItemCategory updateCategory(int categoryId, ItemCategory itemCategory) {
        Optional<ItemCategory> oldItemCategory = this.itemCategoryDAO.findById(categoryId);


        if(oldItemCategory.isPresent()){
            return this.itemCategoryDAO.save(itemCategory);
        }else{
            return null;
        }
    }

    @Override
    public void deleteMenuItemById(int itemId) {

        itemCategoryDAO.deleteById(itemId);
    }
}
