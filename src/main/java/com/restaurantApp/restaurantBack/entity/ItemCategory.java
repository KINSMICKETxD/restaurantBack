package com.restaurantApp.restaurantBack.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "item_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemCategory {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int id;

    @Column(name = "description")
    private String desc;

    @Column(name = "category_name")
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<MenuItem> itemList;
}
