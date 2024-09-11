package com.restaurantApp.restaurantBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuItemDTO {


    private String name;

    private String price;

    private String description;

    private String imagePath;

    private String dietaryTag;

    private boolean isAvailable;

    private String category;

    private List<String> ingredients;

    private Integer cookingTime;


    public void addIngredient(String ingredient){
        if(ingredients == null){
            ingredients = new ArrayList<>();
        }
        ingredients.add(ingredient);
    }
}
