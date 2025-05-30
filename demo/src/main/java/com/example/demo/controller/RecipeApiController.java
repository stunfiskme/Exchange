package com.example.demo.controller;



import org.springframework.http.MediaType;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.RecipeDescriptionDTO;
import com.example.demo.DTO.RecipeInstructionsDTO;
import com.example.demo.DTO.RecipeImageDTO;
import com.example.demo.service.RecipeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/recipes")
public class RecipeApiController {
    @Autowired
    private RecipeService recipeService; 

    //update description
    @PreAuthorize("hasRole('ADMIN') or @recipeSecurity.isOwner(#recipeId)")
    @PatchMapping("/description/{recipeId}")
    public ResponseEntity<String> updateDescription(@PathVariable Long recipeId, @RequestBody @Valid RecipeDescriptionDTO description){
        String d = description.getDescription();
        recipeService.updateDescription(recipeId, d);
        return new ResponseEntity<String>("Description updated successfully!", HttpStatus.OK); 
    }

  
      //update instructions
      @PreAuthorize("hasRole('ADMIN') or @recipeSecurity.isOwner(#recipeId)")
      @PatchMapping("/instructions/{recipeId}")
      public ResponseEntity<String> updateInstructions
      (@PathVariable Long recipeId, @RequestBody @Valid RecipeInstructionsDTO instructions){
            String i = instructions.getInstructions();
          recipeService.patchRecipeInstructions(recipeId, i);
          return new ResponseEntity<String>("Instructions updated successfully!", HttpStatus.OK); 
      }

    //delete a recipe
    @PreAuthorize("hasRole('ADMIN') or @recipeSecurity.isOwner(#recipeId)")
    @DeleteMapping("/{recipeId}")
    public  ResponseEntity<String> deleteRecipe(@PathVariable Long recipeId){
        recipeService.deleteRecipe(recipeId);
        return new ResponseEntity<String>("Recipe deleted successfully!", HttpStatus.OK); 
    }

    //get the recipeImage
    @GetMapping("/recipeImage/{recipeId}")
    public ResponseEntity<byte[]> getRecipeImage(@PathVariable Long recipeId){
       byte[] image = recipeService.getRecipeImage(recipeId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
}
    //update a recipeImage
     @PreAuthorize("hasRole('ADMIN') or @recipeSecurity.isOwner(#recipeId)")
     @PatchMapping("/recipeImage/{recipeId}")
     public ResponseEntity<String> patchRecipeImage
     (@PathVariable Long recipeId, @ModelAttribute @Valid RecipeImageDTO recipeImageDTO) throws IOException{
      recipeService.updateRecipeImage(recipeId, recipeImageDTO);
      return new ResponseEntity<String>("Image updated success!", HttpStatus.OK);
     }
}
