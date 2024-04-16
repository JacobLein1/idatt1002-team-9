package no.ntnu.idatt1005.model.RecipeInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing Recipe
 * @version 1.0
 */
class RecipeTest {

  Recipe recipe;
  @BeforeEach
  void setUp() {
    recipe = new Recipe("1", "Test", null, "Test", 1);
  }

  @Test
  @DisplayName("Test exceptions in constructor")
  void constructor() {
    assertThrows(IllegalArgumentException.class, () -> new Recipe(null, "Test", null, "Test", 1));
    assertThrows(IllegalArgumentException.class, () -> new Recipe("1", null, null, "Test", 1));
    assertThrows(IllegalArgumentException.class, () -> new Recipe("1", "Test", null, null, 1));
    assertThrows(IllegalArgumentException.class, () -> new Recipe("1", "Test", null, "Test", 0));
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
    assertNull(recipe.getIngredients());
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