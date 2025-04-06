package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Recipe;
import com.example.demo.repository.RecipeRepository;

import org.springframework.ui.Model;

@Controller
public class RecipeController {

 @Autowired
    private RecipeRepository recipeRepository;//cange to service!!!!! it looks cooler that way

    @GetMapping("/recipes")
    public String dynamicPage(Model model) {
        model.addAttribute("Recipe", recipeRepository.findAll());
        return "recipes";
    }

    @GetMapping("/addRecipe")
    public String addRecipeForm(Model model){
        model.addAttribute("recipe", new Recipe());
        return "addRecipe";
    }
    @PostMapping("/addRecipe")
    public String addRecipe(@ModelAttribute Recipe recipe, Model model){
        recipeRepository.save(recipe);
        return  "redirect:/recipes";
    }
}
