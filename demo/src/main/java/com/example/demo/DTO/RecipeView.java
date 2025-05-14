package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeView {
    private Long id;
    private String recipeName;
    private String instructions;
    private String description;
    private String firstName;
    private String lastName; 
}
