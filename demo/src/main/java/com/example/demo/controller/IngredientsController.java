package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Ingredients;
import com.example.demo.service.IngredientService;
import com.example.demo.service.RecipeService;

@Controller
@RequestMapping("/addIngredients")
public class IngredientsController {
    
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/{recipe_id}")
    public String addIngredientsForm(Model model, @PathVariable Long recipe_id){
        model.addAttribute("id", recipe_id);
        model.addAttribute("ingredients", new Ingredients());
        return "addIngredients";
    }

    @PostMapping("/{recipe_id}")
    public String addIngredientsToDb
    (@ModelAttribute Ingredients ingredients, Model model, @PathVariable Long recipe_id) 
    throws Exception{
        ingredients.setRecipe(recipeService.getById(recipe_id));//do i need this?
        ingredientService.saveIngredient(ingredients);
        return  "redirect:/addIngredients/" + recipe_id;
    }
}
