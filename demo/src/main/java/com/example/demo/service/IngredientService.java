package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Ingredients;
import com.example.demo.repository.IngredientsRepository;

@Service
public class IngredientService {
    
    @Autowired
    private IngredientsRepository ingredientsRepository;

    public Ingredients saveIngredient(Ingredients i){
       return ingredientsRepository.save(i);
    }

     public List<Ingredients> getByRecipe_id(Long id) throws Exception{
        Optional<List<Ingredients>> ingredients = ingredientsRepository.findByRecipeId(id);
        if(ingredients.isPresent()){
        return ingredients.get();
    }
        else{
            throw new Exception("Ingredients not Found!");
        }
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
