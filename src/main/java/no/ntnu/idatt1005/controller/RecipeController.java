package no.ntnu.idatt1005.controller;

import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.RecipeInfo.Recipe;
import no.ntnu.idatt1005.model.dao.DBConnectionProvider;
import no.ntnu.idatt1005.model.dao.RecipeDAO;

import java.util.List;

/**
 * Class for controlling the functionalities related to the recipes in the database. It
 * has methods for accessing all recipes in the database and getting a specific recipe by its
 * id.
 *
 * @author Sigrid Hoel, Therese Synnøve Rondeel, Mikael Stray Frøyshov
 */
public class RecipeController {
  /**
   * Field for creating an instance of a RecipeDAO-object.
   */
  RecipeDAO recipeDAO;

  /**
   * Field for creating an instance of a GroceryController-object
   */
  GroceryController groceryController;

  /**
   * Constructor for creating an object of the RecipeController class. It initializes the
   * RecipeDAO-object by calling the static instance method of the DBConnectionProvider-class.
   * It also initializes the groceryController-field.
   */
  public RecipeController() {
    recipeDAO = new RecipeDAO(DBConnectionProvider.instance());
    groceryController = new GroceryController();
  }

  /**
   * Method for getting all recipes in the database.
   *
   * @return a list of recipes
   */
  public List<Recipe> getAllRecipes() {
    return recipeDAO.getAllRecipes();
  }

  /**
   * Method for getting a specific recipe based on its id
   *
   * @param id the id of the recipe
   * @return the requested recipe, or null if not found or an error occured
   */
  public Recipe getRecipeById(String id) {
    try {
      int parsedRecipeId = Integer.parseInt(id);
      return recipeDAO.getRecipeById(parsedRecipeId);
    } catch (Exception e) {
      // Instead of using system.out.println, an error message may be sent to the user, letting
      // them know something/what went wrong
      System.out.println("Something went wrong");
      return null;
    }
  }
}
