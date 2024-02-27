package no.ntnu.idatt1005;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestRecipeList {

  @Test
  @DisplayName("Test constructor initialization works")
  void testConstructorInitialization() {
    RecipeList recipeList = new RecipeList();
    assertNotNull(recipeList.getRecipeList());
  }

  @Test
  @DisplayName("Test addRecipe works with valid input")
  void testAddRecipeWorksWithValidInput() {
    RecipeList recipeList = new RecipeList();

    Map<String, Integer> ingredients = new HashMap<>();
    ingredients.put("1", 400);
    ingredients.put("4", 100);
    String instructions = "Add the chicken nuggets to the pasta";
    int numberOfPeople = 4;
    String recipeId = "1";

    assertDoesNotThrow(() ->
        recipeList.addRecipe(recipeId, ingredients, instructions, numberOfPeople));
  }

  @Test
  @DisplayName("Test removeRecipe works with valid input")
  void testRemoveRecipeWorksWithValidInput() {
    RecipeList recipeList = new RecipeList();

    Map<String, Integer> ingredients = new HashMap<>();
    ingredients.put("1", 400);
    ingredients.put("4", 100);
    String instructions = "Add the chicken nuggets to the pasta";
    int numberOfPeople = 4;
    String recipeId = "1";

    recipeList.addRecipe(recipeId, ingredients, instructions, numberOfPeople);

    assertDoesNotThrow(() ->
        recipeList.removeRecipe(recipeId));
  }
}
