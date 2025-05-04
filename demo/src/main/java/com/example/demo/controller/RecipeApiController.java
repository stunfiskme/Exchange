package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        String i = recipeService.getById(recipeId).getDescription();//change to view!? //move logic to service
        return new ResponseEntity<String>(i, HttpStatus.OK); 
    }

    //update description
    @PatchMapping("/description/{recipeId}")
    public ResponseEntity<String> updateDescription(@PathVariable Long recipeId, @RequestBody Map<String, String> description){
        String updatedDescription = description.get("description");
        recipeService.updateDecription(recipeId, updatedDescription);
        return new ResponseEntity<String>("Description updated successfully!", HttpStatus.OK); 
    }
}
