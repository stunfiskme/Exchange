package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.IngredientView;
import com.example.demo.model.Ingredients;
import com.example.demo.repository.IngredientsRepository;

@Service
public class IngredientService {
    
    @Autowired
    private IngredientsRepository ingredientsRepository;

    //save
    public Ingredients saveIngredient(Ingredients i){
       return ingredientsRepository.save(i);
    }

    //get all for a recipe
     public List<IngredientView> getByRecipe_id(Long id) throws Exception{
        List<IngredientView> ingredients = ingredientsRepository.findByRecipeId(id);
        return ingredients;
    }

    //get by id
    public Ingredients getIngredient(Long id){
        return ingredientsRepository.getReferenceById(id);
    }

    //delete
    public void deleteById(Long id){
        ingredientsRepository.deleteById(id);
    }
}
