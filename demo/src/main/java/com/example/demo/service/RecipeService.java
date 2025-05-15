package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.demo.DTO.RecipeRequestDTO;
import com.example.demo.DTO.RecipeView;
import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;
import com.example.exception.ResourceNotFoundException;

import security.CustomUserDetails;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    //save a new recipe
    public Recipe saveRecipe(RecipeRequestDTO r){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Recipe recipe = new Recipe();
        recipe.setUserAccount(userDetails.getUser());
        recipe.setRecipeName(r.getRecipeName());
        recipe.setInstructions(r.getInstructions());
        recipe.setDescription(r.getDescription());
        return recipeRepository.save(recipe);
    }

    //create a list of all recipes as recipeViews
    public List<RecipeView> getAllRecipes(){
        List<Recipe> recipes =  recipeRepository.findAll();
        return recipes.stream().map(r -> new RecipeView(
            r.getId(),
            r.getRecipeName(),
            r.getInstructions(),
            r.getDescription(),
            r.getUserAccount().getFirstName(),
            r.getUserAccount().getLastName()
        )).toList();
    }//N +1 ?????

    //create a recipe view
    public RecipeView createRecipeView(Long id){
        Recipe r = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Recipe not found!"));
        RecipeView rv = 
        new RecipeView(
            r.getId(), 
            r.getRecipeName(), 
            r.getInstructions(), 
            r.getDescription(), 
            r.getUserAccount().getFirstName(),
            r.getUserAccount().getLastName());
        return rv;
    }

    //get recipe by its id
    public Recipe getById(Long id){
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(recipe.isPresent()){
        return recipe.get();
    }
        else{
            throw new ResourceNotFoundException("Recipe not Found!");
        }
    }

    //update instructions for a recipe
    public void patchRecipeInstructions(Long id, String instructions){
        Recipe r = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Recipe not found!"));
        r.setInstructions(instructions);
        recipeRepository.save(r);
    }

    //delete a recipe
    public void deleteRecipe(Long id){
        recipeRepository.deleteById(id);
    }

    //update the description for a recipe
    public void updateDescription(Long id, String description){
        Recipe r = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Recipe not found!"));
        r.setDescription(description);
        recipeRepository.save(r);
    }

    //check if the user is the Author of this recipe
public boolean isUserTheAuthor(Long recipeId) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (!(auth.getPrincipal() instanceof CustomUserDetails)) return false;
    CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
    Long currentUserId = user.getId();
    Optional<Long> author = recipeRepository.findOwnerId(recipeId);
    if(author.isPresent()){
        return author.get().equals(currentUserId);
    }
        else{
            throw new ResourceNotFoundException("Author/Recipe not Found!");
        }
}

}
