package no.ntnu.idatt1005.controller;

import no.ntnu.idatt1005.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.RecipeInfo.Recipe;
import no.ntnu.idatt1005.dao.DBConnectionProvider;
import no.ntnu.idatt1005.dao.RecipeDAO;
import java.util.List;

public class RecipeController {
  RecipeDAO recipeDAO;

  public RecipeController() {
    recipeDAO = new RecipeDAO(DBConnectionProvider.instance());
  }

  public List<Recipe> getAllRecipes() {
    return recipeDAO.getAllRecipes();
  }

  public List<Ingredient> getListOfIngredientForRecipe(String id) {
    try {
      int parsedRecipeId = Integer.parseInt(id);
      return recipeDAO.getIngredientsForRecipe(parsedRecipeId);
    } catch (Exception e) {
      // Instead of using system.out.println, an error message may be sent to the user, letting
      // them know something/what went wrong
      System.out.println("Something went wrong");
      return null;
    }
  }

  public int getAmountOfRecipes() {
    return getAllRecipes().size();
  }
}
