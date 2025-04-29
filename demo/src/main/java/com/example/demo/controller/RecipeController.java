package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
public class RecipeController {

    @Autowired
    private RecipeService recipeService; 
    @Autowired
    private IngredientService ingredientService;

    //get page for a single recipe
    @GetMapping("/recipe/{recipe_id}")
    public String recipePage(Model model,  @PathVariable Long recipe_id) throws Exception{
        //add handlers for null recipe
        model.addAttribute("recipe", recipeService.getById(recipe_id));//hmmm , return a view?
        model.addAttribute("ingredient", ingredientService.getByRecipe_id(recipe_id));//nested viewS
        return "recipe";
    }

    //get all recipes
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
   
    //save a recipe to db and redirect to add recipe page
    @PostMapping("/addRecipe")
    public String addRecipeToDb(@ModelAttribute Recipe recipe, Model model){
        Recipe r = recipeService.saveRecipe(recipe);
        Long id = r.getId();
        return  "redirect:/addIngredients/" + id;
    }

    //get instructions
    @GetMapping("/recipe/get/instructions/{recipeId}")
    @ResponseBody
    public ResponseEntity<String> getInstructions(@PathVariable Long recipeId) throws Exception{
        String i = recipeService.getById(recipeId).getInstructions();//change to view!
        return new ResponseEntity<String>(i, HttpStatus.OK); 
    }

    //update instructions for a recipe
    @PatchMapping("/recipe/update/{recipeId}")
    @ResponseBody
    public ResponseEntity<String> updateInstructions(@PathVariable Long recipeId, @RequestBody Map<String, String> instructions){
        String updatedInstructions = instructions.get("instructions");
recipeService.patchRecipeInstructions(recipeId, updatedInstructions);
        return new ResponseEntity<String>("Instructions updated successfully!", HttpStatus.OK); 
    }
}
