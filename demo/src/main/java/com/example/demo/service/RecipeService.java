package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe saveRecipe(Recipe r){
        return recipeRepository.save(r);
    }

    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }

    public Recipe getById(Long id) throws Exception{
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()){
        return recipe.get();
    }
        else{
            throw new Exception("Recipe not Found!");
        }
    }
}
