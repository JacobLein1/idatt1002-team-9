package no.ntnu.idatt1005;

import java.util.Map;
import java.util.HashMap;

public class Recipe {
  private final Map<String, Integer> ingredients;
  private final String instructions;
  private final int numberOfPeople;

  public Recipe(Map<String, Integer> ingredients, String instructions, int numberOfPeople) {
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.numberOfPeople = numberOfPeople;
  }

  public Map<String, Integer> getIngredients() {
    return ingredients;
  }

  public String getInstructions() {
    return instructions;
  }

  public int getNumberOfPeople() {
    return numberOfPeople;
  }
}
