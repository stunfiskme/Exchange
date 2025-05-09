package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.IngredientView;
import com.example.demo.DTO.IngredientsRequestDTO;
import com.example.demo.model.Ingredients;
import com.example.demo.service.IngredientService;
import com.example.demo.service.RecipeService;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientsApiController {

    @Autowired
    private IngredientService ingredientService;


    //add an ingredient
    @PostMapping("/{recipe_id}")
    public ResponseEntity<IngredientView> addIngredientsToDb(@RequestBody IngredientsRequestDTO ingredientsRequestDTO, @PathVariable Long recipe_id) 
    throws Exception{
        IngredientView dto = ingredientService.addIngredients(recipe_id, ingredientsRequestDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK); 
    }
    
    //get a list of all ingredients in recipe
    @GetMapping("/{recipeId}")
    public ResponseEntity<List<IngredientView>> getIngredients(@PathVariable Long recipeId) throws Exception{
        List<IngredientView> i = ingredientService.getByRecipe_id(recipeId);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    //update an ingredient
    @PutMapping("/{ingredient_id}")
    public ResponseEntity<String> updateIngredient(@RequestBody IngredientsRequestDTO ingredientDetails , @PathVariable Long ingredient_id){
        ingredientService.updateIngredient(ingredient_id, ingredientDetails);
        return new ResponseEntity<>("Ingredient updated successfully!", HttpStatus.OK); 
    }


    //delete an ingredient
    @DeleteMapping("/{ingredient_id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable("ingredient_id") Long ingredient_id){
        ingredientService.deleteById(ingredient_id);
        return new ResponseEntity<String>("Ingredient deleted successfully!", HttpStatus.OK); 
}
}
