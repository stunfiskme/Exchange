package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Recipe;
import com.example.demo.service.IngredientService;
import com.example.demo.service.RecipeService;

import org.springframework.ui.Model;

@Controller
public class RecipeWebController {

    @Autowired
    private RecipeService recipeService; 
    @Autowired
    private IngredientService ingredientService;

    //get recipe.html
    @GetMapping("/recipe/{recipe_id}")
    public String recipePage(Model model,  @PathVariable Long recipe_id) throws Exception{
        //add handlers for null recipe
        model.addAttribute("recipe", recipeService.getById(recipe_id));//hmmm , return a view?
        model.addAttribute("ingredient", ingredientService.getByRecipe_id(recipe_id));//nested viewS
        return "recipe";
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
        model.addAttribute("recipe", new Recipe());
        return "addRecipe";

    }
   
    //save a recipe to db and redirect to addIngredients page
    @PostMapping("/addRecipe")
    public String addRecipeToDb(@ModelAttribute Recipe recipe, Model model){
        Recipe r = recipeService.saveRecipe(recipe);
        Long id = r.getId();
        return  "redirect:/addIngredients/" + id;
    }
    
}
