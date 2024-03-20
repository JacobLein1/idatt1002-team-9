package no.ntnu.idatt1005;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import no.ntnu.idatt1005.Recipe.RecipeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestRecipeList {
  private RecipeList recipeList;
  private Map<String, Integer> ingredients;
  private final String INSTRUCTIONS = "Add the chicken nuggets to the pasta";
  private final int NUMBER_OF_PEOPLE = 4;

  @BeforeEach
  void setUp() {
    recipeList = new RecipeList();
    ingredients = new HashMap<>();

    //her legges 400 gram (tallet kan også representere dl osv) av ingrediens med id 1 til
    //ingredienslista
    ingredients.put("1", 400);
    ingredients.put("4", 100);
  }

  @Test
  @DisplayName("Test constructor initialization works")
  void testConstructorInitialization() {
    assertNotNull(recipeList.getRecipeList());
  }

 /* @Test
  @DisplayName("Test addRecipe works with valid input")
  void testAddRecipeWorksWithValidInput() {
    String recipeId = "1";

    assertDoesNotThrow(() ->
        recipeList.addRecipe(recipeId, ingredients, INSTRUCTIONS, NUMBER_OF_PEOPLE));
  }

  @Test
  @DisplayName("Test addRecipe with invalid input throws")
  void testAddRecipeWithInvalidInputThrows() {
    String recipeId = "1";
    recipeList.addRecipe(recipeId, ingredients, INSTRUCTIONS, NUMBER_OF_PEOPLE);
    assertThrows(IllegalArgumentException.class, () ->
        recipeList.addRecipe(recipeId, ingredients, INSTRUCTIONS, NUMBER_OF_PEOPLE));
  }

  @Test
  @DisplayName("Test removeRecipe works with valid input")
  void testRemoveRecipeWorksWithValidInput() {
    String recipeId = "1";

    recipeList.addRecipe(recipeId, ingredients, INSTRUCTIONS, NUMBER_OF_PEOPLE);

    assertDoesNotThrow(() ->
        recipeList.removeRecipe(recipeId));
  }

  @Test
  @DisplayName("Test removeRecipe with invalid input throws")
  void testRemoveRecipeWithInvalidInputThrows() {
    String recipeId = "1";
    String invalidRecipeId = "2";

    recipeList.addRecipe(recipeId, ingredients, INSTRUCTIONS, NUMBER_OF_PEOPLE);

    assertThrows(IllegalArgumentException.class, () -> recipeList.removeRecipe(invalidRecipeId));
  }*/
}
