package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DTO.IngredientView;
import com.example.demo.model.Ingredients;
import com.example.demo.service.IngredientService;
import com.example.demo.service.RecipeService;

@Controller
public class IngredientsController {
    
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private IngredientService ingredientService;

    //get addIngredients page
    @GetMapping("/addIngredients/{recipe_id}")
    public String addIngredientsForm(Model model, @PathVariable Long recipe_id) throws Exception{
        model.addAttribute("id", recipe_id);
        model.addAttribute("ingredients", new Ingredients());
        return "addIngredients";
    }

    //get list of all ingredients in recipe
    @GetMapping("/get/Ingredients/{recipeId}")
    @ResponseBody
    public ResponseEntity<List<IngredientView>> getIngredients(@PathVariable Long recipeId) throws Exception{
        List<IngredientView> i = ingredientService.getByRecipe_id(recipeId);
        return new ResponseEntity<>(i, HttpStatus.OK);
    }

    //add an ingredient
    public record IngredientDTO(Long id, String ingredientName, String amount, String unitName) {}
    @PostMapping("/addIngredients/{recipe_id}")
    @ResponseBody
    public ResponseEntity<IngredientDTO> addIngredientsToDb(@RequestBody Ingredients ingredients, @PathVariable Long recipe_id) 
    throws Exception{
        ingredients.setRecipe(recipeService.getById(recipe_id));
        Ingredients i = ingredientService.saveIngredient(ingredients);
        IngredientDTO dto = new IngredientDTO(
            i.getId(),
            i.getIngredientName(),
            i.getAmount(),
            i.getUnitName()
        );
        return  new ResponseEntity<>(dto, HttpStatus.OK); 
    }

    //move logic to service!
    //update an ingredient
    @PutMapping("/ingredient/update/{ingredient_id}")
    @ResponseBody
    public ResponseEntity<String> updateIngredientForm(@RequestBody Ingredients ingredientDetails , @PathVariable Long ingredient_id){
        Ingredients ingredient = ingredientService.getIngredient(ingredient_id);
        ingredient.setAmount(ingredientDetails.getAmount());
        ingredient.setIngredientName(ingredientDetails.getIngredientName());
        ingredient.setUnitName(ingredientDetails.getUnitName());
        ingredientService.saveIngredient(ingredient);
        return new ResponseEntity<>("Ingredient updated successfully!", HttpStatus.OK); 
    }

    //delete an ingredient
    @DeleteMapping("/ingredient/delete/{ingredient_id}")
    @ResponseBody
    public ResponseEntity<String> deleteIngredient(@PathVariable("ingredient_id") Long ingredient_id){
        ingredientService.deleteById(ingredient_id);
        return new ResponseEntity<String>("Ingredient deleted successfully!", HttpStatus.OK); 
}

}
