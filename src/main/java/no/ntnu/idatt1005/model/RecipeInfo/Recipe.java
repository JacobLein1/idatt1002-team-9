package no.ntnu.idatt1005.model.RecipeInfo;

import java.util.List;

/**
 * Class for a recipe
 * @version 1.0
 */
public class Recipe {
  private final String recipeID;
  private final String recipeName;
  private final List<Ingredient> ingredients;
  private final String instructions;
  private final int numberOfPeople;

  /**
   * Constructor for recipe
   * @param recipeID id of the recipe
   * @param recipeName name of the recipe
   * @param ingredients ingredients of the recipe
   * @param instructions instructions for the recipe
   * @param numberOfPeople number of people the recipe is for
   */
  public Recipe(String recipeID, String recipeName, List<Ingredient> ingredients,
                String instructions, int numberOfPeople) throws IllegalArgumentException {
    if (recipeID == null || recipeName == null || ingredients.isEmpty() || instructions == null) {
      throw new IllegalArgumentException("There was an attempt to create a recipe either with " +
          "null-values or no ingredients.");
    }
    this.recipeID = recipeID;
    this.recipeName = recipeName;
    this.ingredients = ingredients;
    this.instructions = instructions;
    this.numberOfPeople = numberOfPeople;
  }

  /**
   * Method for getting the id of the recipe
   * @return recipeID
   */
  public String getRecipeID() {
    return recipeID;
  }

  /**
   * Method for getting the name of the recipe
   * @return recipeName
   */
  public String getRecipeName() {
    return recipeName;
  }

  /**
   * Method for getting the ingredients of the recipe
   * @return ingredients
   */
  public List<Ingredient> getIngredients() {
    return ingredients;
  }

  /**
   * Method for getting the instructions of the recipe
   * @return instructions
   */
  public String getInstructions() {
    return instructions;
  }

  /**
   * Method for getting the number of people the recipe is for
   * @return numberOfPeople
   */
  public int getNumberOfPeople() {
    return numberOfPeople;
  }
}
