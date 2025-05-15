package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.IngredientView;
import com.example.demo.DTO.IngredientsRequestDTO;
import com.example.demo.model.Ingredients;
import com.example.demo.repository.IngredientsRepository;
import com.example.exception.ResourceNotFoundException;

@Service
public class IngredientService {
    
    @Autowired
    private IngredientsRepository ingredientsRepository;
    @Autowired
    private RecipeService recipeService;

    //save
    public Ingredients saveIngredient(Ingredients i){
       return ingredientsRepository.save(i);
    }

    //create a new ingredient, save to db, return it
    public IngredientView addIngredients(Long recipeId, IngredientsRequestDTO ingredientsRequestDTO) throws Exception{
        Ingredients newIngredients = new Ingredients();
        newIngredients.setRecipe(recipeService.getById(recipeId));
        newIngredients.setIngredientName(ingredientsRequestDTO.getIngredientName());
        newIngredients.setAmount(ingredientsRequestDTO.getAmount());
        newIngredients.setUnitName(ingredientsRequestDTO.getUnitName());
        Ingredients i = ingredientsRepository.save(newIngredients);
        return new IngredientView(
            i.getId(),
            ingredientsRequestDTO.getIngredientName(),
            ingredientsRequestDTO.getAmount(),
            ingredientsRequestDTO.getUnitName()
        );
    }

    //get all ingredients for a recipe
     public List<IngredientView> getByRecipe_id(Long id){
        List<Ingredients> ingredients = ingredientsRepository.findByRecipeId(id);
        return ingredients.stream().map(i -> new IngredientView(
            i.getId(),
            i.getIngredientName(),
            i.getUnitName(),
            i.getAmount()
        )).toList();
    }

    //get by id
    public Ingredients getIngredient(Long id){
        return ingredientsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Ingredient not found!"));
    }

    //update an ingredient
    public Ingredients updateIngredient(Long id, IngredientsRequestDTO ingredientDetails){
        Ingredients ingredient = ingredientsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException( "Ingredient not found!"));
        ingredient.setAmount(ingredientDetails.getAmount());
        ingredient.setIngredientName(ingredientDetails.getIngredientName());
        ingredient.setUnitName(ingredientDetails.getUnitName());
        return ingredientsRepository.save(ingredient);
    }

    //delete
    public void deleteById(Long id){
        ingredientsRepository.deleteById(id);
    }
}
