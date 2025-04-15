package com.example.demo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Ingredients{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String ingredientName;
    private String unitName;
    private double amount;

    //fk!
    @ManyToOne
    @JoinColumn(name = "recipe_id") 
    private Recipe recipe;
}
