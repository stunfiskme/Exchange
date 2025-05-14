package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.DTO.IngredientsRequestDTO;

@Controller
public class IngredientsWebController {
    
    //get addIngredients page
    @GetMapping("/addIngredients/{recipe_id}")
    public String addIngredientsForm(Model model, @PathVariable Long recipe_id) throws Exception{
        model.addAttribute("id", recipe_id);
        model.addAttribute("ingredients", new IngredientsRequestDTO());
        return "addIngredients";
    }

}
