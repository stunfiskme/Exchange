package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.RecipeRequestDTO;
import com.example.demo.DTO.RecipeView;
import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;

import security.CustomUserDetails;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe saveRecipe(RecipeRequestDTO recipeRequestDTO){
         //create new recipe
         Recipe r = new Recipe();
         r.setId(recipeRequestDTO.getId());
         r.setRecipeName(recipeRequestDTO.getRecipeName());
         r.setDescription(recipeRequestDTO.getDescription());
         r.setInstructions(recipeRequestDTO.getInstructions());
         //set userId for the current Authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        r.setUserAccount(userDetails.getUser());
        return recipeRepository.save(r);
    }

    public List<RecipeView> getAllRecipes(){ 
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(r -> new RecipeView(
            r.getId(),
            r.getRecipeName(),
            r.getInstructions(),
            r.getDescription(),
            r.getUserAccount().getFirstName(),
            r.getUserAccount().getLastName()
        )).toList();
    }

    //get a recipe
    public Recipe getById(Long id){
        return recipeRepository.getReferenceById(id);
    }
   

    //create a recipeView
    public RecipeView createRecipeView(Long id) throws Exception{
        Recipe recipe = recipeRepository.findById(id).orElseThrow();
        return new RecipeView(recipe.getId(),
                                recipe.getRecipeName(),
                                recipe.getDescription(),
                                recipe.getInstructions(),
                                recipe.getUserAccount().getFirstName(),
                                recipe.getUserAccount().getLastName());

    }

    //update instructions for a recipe
    public Recipe patchRecipeInstructions(Long id, String instructions){
        Recipe r = recipeRepository.findById(id).orElseThrow();
        r.setInstructions(instructions);
        return recipeRepository.save(r);
    }

    //delete a recipe
    public void deleteRecipe(Long id){
        recipeRepository.deleteById(id);
    }

    //update the description for a recipe
    public Recipe updateDescription(Long id, String description){
        Recipe r = recipeRepository.findById(id).orElseThrow();
        r.setDescription(description);
        return recipeRepository.save(r);
    }
}
