package com.restaurantApp.restaurantBack.service.menuItem;

import com.restaurantApp.restaurantBack.dao.MenuItemDAO;
import com.restaurantApp.restaurantBack.dto.MenuItemDTO;
import com.restaurantApp.restaurantBack.entity.Ingredient;
import com.restaurantApp.restaurantBack.entity.MenuItem;
import com.restaurantApp.restaurantBack.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class MenuItemServiceImpl implements MenuItemService{

    private MenuItemDAO menuItemDAO;


    @Autowired
    public MenuItemServiceImpl(MenuItemDAO menuItemDAO){
        this.menuItemDAO = menuItemDAO;
    }

    @Override
    public MenuItemDTO findById(int itemId) {
        MenuItem menuItem = menuItemDAO.findById(itemId).orElseThrow(
                ()-> new ItemNotFoundException("Item with ID = "+itemId+" Is not found.")
        );
        return convertToDTO(menuItem);

    }

    @Override
    public List<MenuItemDTO> findAll() {
        List<MenuItem> menuItems = menuItemDAO.findAll();
        if(menuItems == null){
            throw new ItemNotFoundException("There is no Menu Items in the restaurant.");
        }
        List<MenuItemDTO> menuItemDTOS = new ArrayList<>();
        for(MenuItem m : menuItems){
            menuItemDTOS.add(convertToDTO(m));
        }
        return menuItemDTOS;
    }

    @Override
    public MenuItem save(MenuItem menuItem) {

        return menuItemDAO.save(menuItem);
    }

    @Override
    public void deleteMenuItemById(int itemId) {

        menuItemDAO.deleteById(itemId);
    }

    @Override
    public HashMap<String, List<MenuItemDTO>> getMenuItemsOrganizedByCategory() {


        List<MenuItem> menuItems = this.menuItemDAO.findAll();

        List<MenuItemDTO> menuItemDTOS = new ArrayList<>();
        for(MenuItem c : menuItems){
            menuItemDTOS.add(convertToDTO(c));
        }
        return menuItemDTOS.stream().collect(Collectors.groupingBy(
                item -> item.getCategory() != null ? item.getCategory() : "Uncategorized",
                HashMap::new,Collectors.toList()
        ));
    }

    public MenuItemDTO convertToDTO(MenuItem menuItem){
        MenuItemDTO menuItemDTO = new MenuItemDTO();
        menuItemDTO.setName(menuItem.getName());
        menuItemDTO.setDescription(menuItem.getDescription());
        menuItemDTO.setPrice(menuItem.getPrice());
        menuItemDTO.setAvailable(menuItem.isAvailable());
        menuItemDTO.setCategory(menuItem.getCategory().getName());
        menuItemDTO.setImagePath(menuItem.getImagePath());
        menuItemDTO.setDietaryTag(menuItem.getDietaryTag());
        List<Ingredient> ingredients = menuItem.getIngredients();
        for(Ingredient ingredient : ingredients){
            menuItemDTO.addIngredient(ingredient.getName());
        }
        return menuItemDTO;
    }
}
