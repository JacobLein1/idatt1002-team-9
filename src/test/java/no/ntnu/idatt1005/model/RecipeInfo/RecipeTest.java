package no.ntnu.idatt1005.model.RecipeInfo;

import no.ntnu.idatt1005.model.grocery.Grocery;
import no.ntnu.idatt1005.unit.UnitsE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing Recipe
 * @version 1.0
 */
class RecipeTest {

  Recipe recipe;
  List<Ingredient> ingredients;
  @BeforeEach
  void setUp() {
    ingredients = List.of(new Ingredient(new Grocery("test",null,"1", UnitsE.ARTICLE), 1));
    recipe = new Recipe("1", "Test", ingredients, "Test", 1);
  }

  @Test
  @DisplayName("Test exceptions in constructor")
  void constructor() {
    assertThrows(IllegalArgumentException.class, () -> new Recipe(null, "Test", ingredients, "Test", 1));
    assertThrows(IllegalArgumentException.class, () -> new Recipe("1", null, ingredients, "Test", 1));
    assertThrows(IllegalArgumentException.class, () -> new Recipe("1", "Test", new ArrayList<>(), "test", 1));
    assertThrows(IllegalArgumentException.class, () -> new Recipe("1", "Test", ingredients, null, 1));
  }

  @Test
  @DisplayName("Test getRecipeID() method")
  void getRecipeID() {
    assertEquals("1", recipe.getRecipeID());
  }

  @Test
  @DisplayName("Test getRecipeName() method")
  void getRecipeName() {
    assertEquals("Test", recipe.getRecipeName());
  }

  @Test
  @DisplayName("Test getIngredients() method")
  void getIngredients() {
    assertEquals(ingredients, recipe.getIngredients());
  }

  @Test
  @DisplayName("Test getInstructions() method")
  void getInstructions() {
    assertEquals("Test", recipe.getInstructions());
  }

  @Test
  @DisplayName("Test getNumberOfPeople() method")
  void getNumberOfPeople() {
    assertEquals(1, recipe.getNumberOfPeople());
  }
}