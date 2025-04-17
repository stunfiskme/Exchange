package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Ingredients;
import com.example.demo.service.IngredientService;
import com.example.demo.service.RecipeService;

@Controller
public class IngredientsController {
    
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/addIngredients/{recipe_id}")
    public String addIngredientsForm(Model model, @PathVariable Long recipe_id){
        model.addAttribute("id", recipe_id);
        model.addAttribute("ingredients", new Ingredients());
        return "addIngredients";
    }

    @PostMapping("/addIngredients/{recipe_id}")
    public String addIngredientsToDb
    (@ModelAttribute Ingredients ingredients, Model model, @PathVariable Long recipe_id) 
    throws Exception{
        ingredients.setRecipe(recipeService.getById(recipe_id));//do i need this?
        ingredientService.saveIngredient(ingredients);
        return  "redirect:/addIngredients/" + recipe_id;
    }

    @PatchMapping("/recipe/update/{ingredient_id}")
    public String updateIngredientForm(){
        //look up db row
        //use form to make changes
        return "index";//change to form
    }

    @DeleteMapping("/recipe/delete/{ingredient_id}")
@ResponseBody
public ResponseEntity<String> deleteIngredient(@PathVariable("ingredient_id") Long ingredient_id){
    ingredientService.deleteById(ingredient_id);
    return new ResponseEntity<String>("Ingredient deleted successfully!", HttpStatus.OK); 
}

}
