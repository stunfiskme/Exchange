package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ingredients;

@Repository
public interface IngredientsRepository extends JpaRepository <Ingredients, Long> {
    Optional<List<Ingredients>> findByRecipeId(Long RecipeId);
}
