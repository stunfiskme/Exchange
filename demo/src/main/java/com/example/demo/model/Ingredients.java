package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ingredients{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String ingredientName;
    private String unitName;
    private String amount;

    //fk!
    @ManyToOne
    @JoinColumn(name = "recipe_id") 
    private Recipe recipe;
}
