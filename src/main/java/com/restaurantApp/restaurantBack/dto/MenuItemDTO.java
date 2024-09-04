package com.restaurantApp.restaurantBack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuItemDTO {

    private int id;

    private String name;

    private String price;

    private String description;

    private String imagePath;

    private String dietaryTag;

    private boolean isAvailable;

    private String category;
}
