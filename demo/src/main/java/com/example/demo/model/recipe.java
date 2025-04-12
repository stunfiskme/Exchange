package com.example.demo.model;

import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Recipe{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String recipeName;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "TEXT")
    private String instructions;

    //
    @ManyToOne
    @JoinColumn(name = "Author") 
    private UserAccounts userAccount;
    //
    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredients> ingredients;
}
