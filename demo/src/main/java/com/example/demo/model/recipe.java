package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
    @Size(max = 280)
    private String description;
    @Column(columnDefinition = "TEXT")
    @Size(max = 1000)
    private String instructions;

    //fk to UserAccounts
    @ManyToOne
    @JoinColumn(name = "UserAccounts_id") 
    private UserAccounts userAccount;
    //
    @OneToMany( mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<Ingredients> ingredients = new ArrayList<>();
}
