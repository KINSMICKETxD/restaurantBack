package com.restaurantApp.restaurantBack.controller;


import com.restaurantApp.restaurantBack.dao.ItemCategoryDAO;
import com.restaurantApp.restaurantBack.entity.ItemCategory;
import com.restaurantApp.restaurantBack.service.itemCategory.ItemCategoryService;
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
    public ItemCategory findById(@PathVariable int categoryId){

        return this.itemCategoryService.findItemCategoryById(categoryId);
    }

    @GetMapping("/{categoryName}")
    public ItemCategory findByName(@PathVariable String categoryName){

        return this.itemCategoryService.findItemCategoryByName(categoryName);
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
