package no.ntnu.idatt1005.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import no.ntnu.idatt1005.GroceryInfo.Grocery;
import no.ntnu.idatt1005.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.RecipeInfo.Recipe;
import no.ntnu.idatt1005.dao.DBConnectionProvider;
import no.ntnu.idatt1005.dao.RecipeDAO;
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

  public HashMap<String, String> getIngredientsForRecipe(String id) {
    HashMap<String, String> ingredientsInfo = new HashMap<>();
    List<Ingredient> listOfIngredients = getListOfIngredientForRecipe(id);

    for (Ingredient ingredient : listOfIngredients) {
      Grocery grocery = groceryController.getGroceryById(ingredient.getGroceryId());
      String name = grocery.getName();
      String amountAndUnit = ingredient.getAmount() + " " + grocery.getUnit().getUnit();
      ingredientsInfo.put(name, amountAndUnit);
    }

    return ingredientsInfo;
  }

  public List<Ingredient> getListOfIngredientForRecipe(String id) {
    try {
      int parsedRecipeId = Integer.parseInt(id);
      return recipeDAO.getIngredientsForRecipe(parsedRecipeId);
    } catch (Exception e) {
      // Instead of using system.out.println, an error message may be sent to the user, letting
      // them know something/what went wrong
      System.out.println("Something went wrong");
      return new ArrayList<>();
    }
  }
}
