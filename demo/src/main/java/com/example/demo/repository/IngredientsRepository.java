package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ingredients;

@Repository
public interface IngredientsRepository extends JpaRepository <Ingredients, Long> {
    List<Ingredients> findByRecipeId(Long recipeId);
}
