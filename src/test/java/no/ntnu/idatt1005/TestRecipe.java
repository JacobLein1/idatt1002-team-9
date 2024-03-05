package no.ntnu.idatt1005;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRecipe {

  @Test
  @DisplayName("Test constructor with valid input")
  void testConstructorWithValidInput() {
    Map<String, Double> ingredients = new HashMap<>();
    ingredients.put("1", 400.0);
    ingredients.put("4", 100.0);
    String instructions = "Add the chicken nuggets to the pasta";
    int numberOfPeople = 4;

    Recipe recipe = new Recipe(ingredients, instructions, numberOfPeople);
    assertEquals(ingredients, recipe.getIngredients());
    assertEquals(instructions, recipe.getInstructions());
    assertEquals(numberOfPeople, recipe.getNumberOfPeople());
  }
}
