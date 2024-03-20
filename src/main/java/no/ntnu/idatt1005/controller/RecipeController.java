package no.ntnu.idatt1005.controller;

import no.ntnu.idatt1005.Recipe.Recipe;
import no.ntnu.idatt1005.dao.DBConnectionProvider;
import no.ntnu.idatt1005.dao.RecipeDAO;
import java.util.List;
import java.util.List.*;

public class RecipeController {
  RecipeDAO recipeDAO;

  public RecipeController() {
    recipeDAO = new RecipeDAO(DBConnectionProvider.instance());
  }

  public List<Recipe> getAllRecipes() {
    return recipeDAO.getRecipes();
  }
  public int getAmountOfRecipes() {
    return getAllRecipes().size();
  }
}
