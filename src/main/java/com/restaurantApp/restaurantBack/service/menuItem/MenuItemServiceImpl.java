package com.restaurantApp.restaurantBack.service.menuItem;

import com.restaurantApp.restaurantBack.dao.MenuItemDAO;
import com.restaurantApp.restaurantBack.dto.MenuItemDTO;
import com.restaurantApp.restaurantBack.entity.MenuItem;
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
        return convertToDTO(this.menuItemDAO.findById(itemId).get());
    }

    @Override
    public List<MenuItem> findAll() {
        return menuItemDAO.findAll();
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
        menuItemDTO.setId(menuItem.getId());
        menuItemDTO.setName(menuItem.getName());
        menuItemDTO.setDescription(menuItem.getDescription());
        menuItemDTO.setPrice(menuItem.getPrice());
        menuItemDTO.setAvailable(menuItem.isAvailable());
        menuItemDTO.setCategory(menuItem.getCategory().getName());
        menuItemDTO.setImagePath(menuItem.getImagePath());
        menuItemDTO.setDietaryTag(menuItem.getDietaryTag());

        return menuItemDTO;
    }
}
