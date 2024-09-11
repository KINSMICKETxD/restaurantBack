package com.restaurantApp.restaurantBack.controller;


import com.restaurantApp.restaurantBack.dto.MenuItemDTO;
import com.restaurantApp.restaurantBack.entity.Customer;
import com.restaurantApp.restaurantBack.entity.MenuItem;
import com.restaurantApp.restaurantBack.service.menuItem.MenuItemService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/menuItems")
public class MenuItemController {

    private MenuItemService menuItemService;


    @Autowired
    public MenuItemController(MenuItemService menuItemService){
        this.menuItemService = menuItemService;
    }


    @GetMapping
    public List<MenuItemDTO> getAllItems(){
        return this.menuItemService.findAll();
    }
    @GetMapping("/{menuItemId}")
    public ResponseEntity<MenuItemDTO> getItemById(@PathVariable("menuItemId") int itemId){
        MenuItemDTO menuItemDTO = this.menuItemService.findById(itemId);

        return ResponseEntity.ok(menuItemDTO);
    }

    @PostMapping
    public MenuItem addNewItem(@RequestBody MenuItem newItem){

        MenuItem createdItem = menuItemService.save(newItem);

        return createdItem;
    }

    @PutMapping
    public MenuItem updateItem(@RequestBody MenuItem menuItem){

       return  menuItemService.save(menuItem);
    }

    @DeleteMapping("/{menuItemId}")
    public void deleteItemById(@PathVariable int menuItemId){

        this.menuItemService.deleteMenuItemById(menuItemId);
    }
    @GetMapping("/menu")
    public HashMap<String,List<MenuItemDTO>> getMenuItemsOrganizedByCategory(){


        return this.menuItemService.getMenuItemsOrganizedByCategory();
    }


}
