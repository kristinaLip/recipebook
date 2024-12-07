package com.example.recipebook.controller;

import com.example.recipebook.model.Recipe;
import com.example.recipebook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    // intro
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    // recipes
    @GetMapping("/recipes")
    public String showRecipesPage(Model model) {
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "recipes";
    }

    // Display individual recipe details
    @GetMapping("/recipe/{id}")
    public String showRecipeDetail(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            return "recipeDetail";
        }
        return "error";
    }

    // Edit Recipe Page
    @GetMapping("/recipe/edit/{id}")
    public String showEditRecipePage(@PathVariable Long id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        if (recipe != null) {
            model.addAttribute("recipe", recipe);
            return "editRecipe";
        }
        return "error";
    }

    // Update Recipe
    @PostMapping("/recipe/edit/{id}")
    public String updateRecipe(@PathVariable Long id, @ModelAttribute Recipe recipe) {
        recipeService.updateRecipe(id, recipe);
        return "redirect:/recipe/" + id;
    }

    // Show the "Add New Recipe" page
    @GetMapping("/addRecipe")
    public String showAddRecipePage(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "addRecipe";
    }

    // Handle form submission for adding a new recipe
    @PostMapping("/addRecipe")
    public String addRecipe(@ModelAttribute Recipe recipe) {
        recipeService.saveRecipe(recipe);
        return "redirect:/recipes";
    }
}
