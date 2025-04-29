package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;

import security.CustomUserDetails;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe saveRecipe(Recipe r){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        r.setUserAccount(userDetails.getUser());
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

    //update
    public Recipe patchRecipeInstructions(Long id, String instructions){
        Recipe r = recipeRepository.findById(id).orElseThrow();
        r.setInstructions(instructions);
        return recipeRepository.save(r);
    }
}
