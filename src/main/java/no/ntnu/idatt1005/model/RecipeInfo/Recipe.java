package no.ntnu.idatt1005.model.RecipeInfo;

import java.util.List;

public class Recipe {
  private final String recipeID;
  private final String recipeName;
  private final List<Ingredient> ingredients;
  private final String instructions;
  private final int numberOfPeople;

  public Recipe(String recipeID, String recipeName, List<Ingredient> ingredients,
                String instructions, int numberOfPeople) throws IllegalArgumentException {
    if (recipeID == null || recipeName == null || ingredients.isEmpty() || instructions == null) {
      throw new IllegalArgumentException("There was an attempt to create a recipe either with " +
          "null-values or no ingredients.");
    }
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.numberOfPeople = numberOfPeople;
    this.recipeID = recipeID;
    this.recipeName = recipeName;
  }

  public String getRecipeID() {
    return recipeID;
  }

  public String getRecipeName() {
    return recipeName;
  }

  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  public String getInstructions() {
    return instructions;
  }

  public int getNumberOfPeople() {
    return numberOfPeople;
  }

}
