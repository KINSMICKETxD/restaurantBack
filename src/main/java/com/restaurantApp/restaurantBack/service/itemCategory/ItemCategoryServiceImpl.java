package com.restaurantApp.restaurantBack.service.itemCategory;

import com.restaurantApp.restaurantBack.dao.ItemCategoryDAO;
import com.restaurantApp.restaurantBack.dao.MenuItemDAO;
import com.restaurantApp.restaurantBack.dto.ItemCategoryDTO;
import com.restaurantApp.restaurantBack.dto.MenuItemDTO;
import com.restaurantApp.restaurantBack.entity.ItemCategory;
import com.restaurantApp.restaurantBack.entity.MenuItem;
import com.restaurantApp.restaurantBack.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService{

    private ItemCategoryDAO itemCategoryDAO;

    private MenuItemDAO menuItemDAO;


    @Autowired
    public ItemCategoryServiceImpl(ItemCategoryDAO itemCategoryDAO,MenuItemDAO menuItemDAO){
        this.itemCategoryDAO = itemCategoryDAO;
        this.menuItemDAO = menuItemDAO;
    }


    @Override
    public ItemCategoryDTO findItemCategoryById(int categoryId) {
        return convertToDTO(itemCategoryDAO.findById(categoryId).orElseThrow(
                ()->new CategoryNotFoundException("Category with id = "+categoryId+" is not found."))
        );
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

    @Override
    public List<MenuItemDTO> getAllMenuItemsByCategoryId(int categoryId) {


        ItemCategory itemCategory = this.itemCategoryDAO.findById(categoryId).orElseThrow(
                ()-> new CategoryNotFoundException("Category with id = "+categoryId+" is not found.")
        );

        List<MenuItem> menuItems = itemCategory.getItemList();
        List<MenuItemDTO> menuItemDTOS = new ArrayList<>();
        for(MenuItem m : menuItems){
            menuItemDTOS.add(convertToDTO(m));
        }

        return menuItemDTOS;

    }

    private MenuItemDTO convertToDTO(MenuItem menuItem){
        MenuItemDTO menuItemDTO = new MenuItemDTO();
        menuItemDTO.setCategory(menuItem.getCategory().getName());
        menuItemDTO.setName(menuItem.getName());
        menuItemDTO.setAvailable(menuItem.isAvailable());
        menuItemDTO.setPrice(menuItem.getPrice());
        menuItemDTO.setDietaryTag(menuItem.getDietaryTag());
        menuItemDTO.setDescription(menuItem.getDescription());
        menuItemDTO.setImagePath(menuItem.getImagePath());

        return menuItemDTO;
    }

    private ItemCategoryDTO convertToDTO(ItemCategory itemCategory){
        ItemCategoryDTO itemCategoryDTO = new ItemCategoryDTO();

        itemCategoryDTO.setCategoryName(itemCategory.getName());
        itemCategoryDTO.setDescription(itemCategory.getDesc());
        itemCategoryDTO.setNumberOfItems(itemCategory.getItemList().size());

        return itemCategoryDTO;
    }
}
