package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.RecipeService;

@RestController
@RequestMapping("/api/recipes")
public class RecipeApiController {
    @Autowired
    private RecipeService recipeService; 

    //get description
    @GetMapping("/description/{recipeId}")
    public ResponseEntity<String> getDescription(@PathVariable Long recipeId) throws Exception{
        String i = recipeService.getById(recipeId).getDescription();
        return new ResponseEntity<String>(i, HttpStatus.OK); 
    }

    //update description
    @PatchMapping("/description/{recipeId}")
    public ResponseEntity<String> updateDescription(@PathVariable Long recipeId, @RequestBody Map<String, String> description){
        String updatedDescription = description.get("description");
        recipeService.updateDescription(recipeId, updatedDescription);
        return new ResponseEntity<String>("Description updated successfully!", HttpStatus.OK); 
    }

      //get instructions
      @GetMapping("/instructions/{recipeId}")
      public ResponseEntity<String> getInstructions(@PathVariable Long recipeId) throws Exception{
          String i = recipeService.getById(recipeId).getInstructions();
          return new ResponseEntity<String>(i, HttpStatus.OK); 
      }
  
      //update instructions
      @PatchMapping("/instructions/{recipeId}")
      public ResponseEntity<String> updateInstructions(@PathVariable Long recipeId, @RequestBody Map<String, String> instructions){
          String updatedInstructions = instructions.get("instructions");
          recipeService.patchRecipeInstructions(recipeId, updatedInstructions);
          return new ResponseEntity<String>("Instructions updated successfully!", HttpStatus.OK); 
      }

    //delete a recipe
    @DeleteMapping("/{recipeId}")
    public  ResponseEntity<String> deleteRecipe(@PathVariable Long recipeId){
        recipeService.deleteRecipe(recipeId);
        return new ResponseEntity<String>("Recipe deleted successfully!", HttpStatus.OK); 
    }
}
