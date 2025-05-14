package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

    @Query("SELECT r.userAccount.id FROM Recipe r WHERE r.id = :recipeId")
    Optional<Long> findOwnerId(@Param("recipeId") Long recipeId);

}
