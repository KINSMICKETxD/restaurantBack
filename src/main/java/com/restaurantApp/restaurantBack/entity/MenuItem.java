package com.restaurantApp.restaurantBack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="menu_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "item_description")
    private String description;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "dietary_tag")
    private String DietaryTag;

    @Column(name = "is_available")
    private boolean isAvailable;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,
    CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private ItemCategory category;
}
