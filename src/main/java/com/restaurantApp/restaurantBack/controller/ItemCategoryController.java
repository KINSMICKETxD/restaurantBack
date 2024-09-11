package com.restaurantApp.restaurantBack.controller;


import com.restaurantApp.restaurantBack.dto.ItemCategoryDTO;
import com.restaurantApp.restaurantBack.dto.MenuItemDTO;
import com.restaurantApp.restaurantBack.entity.ItemCategory;
import com.restaurantApp.restaurantBack.service.itemCategory.ItemCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ItemCategoryController {

    private ItemCategoryService itemCategoryService;


    public ItemCategoryController(ItemCategoryService itemCategoryDAO){

        this.itemCategoryService = itemCategoryDAO;
    }

    @GetMapping
    public List<ItemCategory> findAll(){

        return this.itemCategoryService.findAll();
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ItemCategoryDTO> findById(@PathVariable int categoryId){

        return ResponseEntity.ok(this.itemCategoryService.findItemCategoryById(categoryId));
    }

    @GetMapping("/{categoryId}/items")
    public ResponseEntity<List<MenuItemDTO>> getAllMenuItemsByCategoryId(@PathVariable("categoryId")int categoryId){

        List<MenuItemDTO> menuItemDTOS = this.itemCategoryService.getAllMenuItemsByCategoryId(categoryId);
        return ResponseEntity.ok(menuItemDTOS);
    }

    @PostMapping
    public ItemCategory addNewCategory(@RequestBody ItemCategory newItemCategory){

       return  this.itemCategoryService.save(newItemCategory);
    }

    @PutMapping("/{categoryId}")
    public ItemCategory updateItemCategory(@PathVariable int categoryId,@RequestBody ItemCategory itemCategory){

        return this.itemCategoryService.updateCategory(categoryId,itemCategory);
    }


}
