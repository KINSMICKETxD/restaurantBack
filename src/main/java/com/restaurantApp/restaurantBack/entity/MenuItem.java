package com.restaurantApp.restaurantBack.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="menu_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "cooking_time")
    private Integer cookingTime;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,
    CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "category_id")
    private ItemCategory category;

    @OneToMany(mappedBy = "menuItem",cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {
            CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH
    })
    @JoinTable(name = "menu_item_ingredients",
                joinColumns = @JoinColumn(name = "item_id"),
                inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<Ingredient> ingredients;
}
