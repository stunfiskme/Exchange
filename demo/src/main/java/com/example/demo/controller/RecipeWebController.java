package com.example.demo.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.DTO.RecipeRequestDTO;
import com.example.demo.model.Recipe;
import com.example.demo.service.IngredientService;
import com.example.demo.service.RecipeService;

import jakarta.validation.Valid;

import org.springframework.ui.Model;

@Controller
public class RecipeWebController {

    @Autowired
    private RecipeService recipeService; 
    @Autowired
    private IngredientService ingredientService;


    //get recipe.html ,if author/Admin, or recipeViewOnly.html if not author of recipe
    @GetMapping("/recipe/{recipe_id}")
    public String recipePage(Model model,  @PathVariable Long recipe_id) throws Exception{
        model.addAttribute("recipe", recipeService.createRecipeView(recipe_id));
        model.addAttribute("ingredient", ingredientService.getByRecipe_id(recipe_id));
        //check if Admin
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                          .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if(isAdmin){
            return "recipe";
        }
        if(recipeService.isUserTheAuthor(recipe_id)){
            return "recipe";
        }
        return "recipeViewOnly";
    }

    //get recipes.html
    @GetMapping("/recipes")
    public String dynamicPage(Model model) {
        model.addAttribute("Recipe", recipeService.getAllRecipes());
        return "recipes";
    }

    //get add recipe page 
    @GetMapping("/addRecipe")
    public String addRecipeForm(Model model){
        model.addAttribute("recipe", new RecipeRequestDTO());
        return "addRecipe";

    }
   
    //save a recipe to db and redirect to addIngredients page
    @PostMapping("/addRecipe")
    public String addRecipeToDb(@ModelAttribute @Valid RecipeRequestDTO recipe) throws IOException{
        Recipe r = recipeService.saveRecipe(recipe);
        Long id = r.getId();
        return  "redirect:/addIngredients/" + id;
    }
    
}
