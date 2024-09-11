package com.restaurantApp.restaurantBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemCategoryDTO {

    private String description;

    private String CategoryName;

    private int numberOfItems;
    
}
