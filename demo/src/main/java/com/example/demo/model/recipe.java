package com.example.demo.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recipe{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long recipe_id;
    private String recipeName;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String instructions;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredients> ingredients;
}
