package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Ingredients;
import com.example.demo.model.Recipe;
import com.example.demo.service.IngredientService;
import com.example.demo.service.RecipeService;

import org.springframework.ui.Model;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService; 
    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/recipe/{recipe_id}")
    public String recipePage(Model model,  @PathVariable Long recipe_id) throws Exception{
        //add handlers for null recipe
        model.addAttribute("recipe", recipeService.getById(recipe_id));
        model.addAttribute("ingredient", ingredientService.getByRecipe_id(recipe_id));
        return "recipe";
    }

    @GetMapping("/recipes")
    public String dynamicPage(Model model) {
        model.addAttribute("Recipe", recipeService.getAllRecipes());
        return "recipes";
    }

    @GetMapping("/addRecipe")
    public String addRecipeForm(Model model){
        model.addAttribute("recipe", new Recipe());
        return "addRecipe";

    }
   
    @PostMapping("/addRecipe")
    public String addRecipeToDb(@ModelAttribute Recipe recipe, Model model){
        Recipe r = recipeService.saveRecipe(recipe);
        Long id = r.getId();
        return  "redirect:/addIngredients/" + id;
    }
}
