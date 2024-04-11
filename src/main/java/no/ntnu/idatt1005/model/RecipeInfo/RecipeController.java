package no.ntnu.idatt1005.model.RecipeInfo;

import java.util.ArrayList;
import java.util.HashMap;

import no.ntnu.idatt1005.model.grocery.Grocery;
import no.ntnu.idatt1005.model.grocery.GroceryController;
import no.ntnu.idatt1005.model.dao.DBConnectionProvider;
import no.ntnu.idatt1005.model.dao.RecipeDAO;

import java.util.List;

public class RecipeController {
  RecipeDAO recipeDAO;
  GroceryController groceryController;

  public RecipeController() {
    recipeDAO = new RecipeDAO(DBConnectionProvider.instance());
    groceryController = new GroceryController();
  }

  public List<Recipe> getAllRecipes() {
    return recipeDAO.getAllRecipes();
  }

  public List<Ingredient> getIngredientsForRecipe(String recipeId) {
    int recipeIdInt = Integer.parseInt(recipeId);

    return recipeDAO.getRecipeById(recipeIdInt).getIngredients();
  }

  public Recipe getRecipeById(String id) throws IllegalArgumentException {
    try {
      int parsedRecipeId = Integer.parseInt(id);
      return recipeDAO.getRecipeById(parsedRecipeId);
    } catch (Exception e) {
      throw new IllegalArgumentException("Recipe not found");
    }
  }
}
