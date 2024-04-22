package no.ntnu.idatt1005.model.RecipeInfo;

import no.ntnu.idatt1005.model.grocery.Grocery;
import no.ntnu.idatt1005.model.unit.UnitsE;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing Ingredient
 * @version 1.0
 */
class IngredientTest {

  @Test
  @DisplayName("Test exceptions in constructor")
  void constructor() {
    assertThrows(IllegalArgumentException.class, () -> new Ingredient(null, 1));
  }

  @Test
  @DisplayName("Test getGrocery() method")
  void getGrocery() {
    Ingredient ingredient = new Ingredient(new Grocery("Test", null, "1", UnitsE.ARTICLE), 1);
    assertEquals("Test", ingredient.getGrocery().getName());
    assertEquals("1", ingredient.getGrocery().getId());
  }

  @Test
  void getAmount() {
    Ingredient ingredient = new Ingredient(new Grocery("Test", null, "1", UnitsE.ARTICLE), 1);
    assertEquals(1, ingredient.getAmount());
  }
}