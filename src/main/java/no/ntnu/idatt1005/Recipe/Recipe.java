package no.ntnu.idatt1005.Recipe;

import java.util.Map;
import java.util.HashMap;

public class Recipe {
  private final Map<String, Double> ingredients;
  private final String instructions;
  private final int numberOfPeople;

  public Recipe(Map<String, Double> ingredients, String instructions, int numberOfPeople) {
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.numberOfPeople = numberOfPeople;
  }

  public Map<String, Double> getIngredients() {
    return ingredients;
  }

  public String getInstructions() {
    return instructions;
  }

  public int getNumberOfPeople() {
    return numberOfPeople;
  }

  private Map<String, Double>  multiplyIngredients(int numberOfPeople) {
    HashMap<String, Double> updatedMap = new HashMap<>();

    for (Map.Entry<String, Double> entry : this.ingredients.entrySet()) {
      updatedMap.put(entry.getKey(), entry.getValue() * numberOfPeople);
    }
    return updatedMap;
  }

}
