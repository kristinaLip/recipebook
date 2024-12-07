package com.example.recipebook.service;

import com.example.recipebook.model.Recipe;
import com.example.recipebook.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    // Get all recipes
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    // Get a recipe by ID
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.orElse(null); // Return the recipe or null if not found
    }

    public void updateRecipe(Long id, Recipe recipe) {
        if (recipeRepository.existsById(id)) {
            recipe.setId(id);
            recipeRepository.save(recipe);  // Save updated recipe to the database
        }
    }
    // Add a new recipe
    public void addRecipe(Recipe recipe) {
        recipeRepository.save(recipe); // Save the recipe to the database
    }

    // Method to save a new recipe
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }


}
