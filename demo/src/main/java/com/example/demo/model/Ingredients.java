package com.example.demo.model;


import jakarta.persistence.*;

@Entity
public class Ingredients{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long ingredient_id;
    private String ingredientName;
    private String unitName;
    private double amount;

    //fk!
    @ManyToOne
    @JoinColumn(name = "recipe_id") 
    private Recipe recipe;
}
