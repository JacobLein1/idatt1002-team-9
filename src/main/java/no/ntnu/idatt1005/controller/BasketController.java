package no.ntnu.idatt1005.controller;

import java.util.*;

import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.grocery.Grocery;

/**
 * The BasketController class is responsible for managing the shopping list.
 * It interacts with inventory, grocery, and recipe controllers to determine
 * the ingredients needed to purchase based on recipes and current inventory.
 */
public class BasketController {
  InventoryController inventoryController;
  
  GroceryController groceryController;

  RecipeController recipeController;

  public BasketController() {
    inventoryController = new InventoryController();
    groceryController = new GroceryController();
    recipeController = new RecipeController();
  }

  /**
   * Generates a shopping list from a basket of recipes with their respective amounts.
   *
   * @param recipeAmountMap A map of recipe IDs to their quantities.
   * @return A HashMap where keys are food names and values are strings indicating the amount and unit needed.
   */
  public HashMap<String, String> getShoppingListFromBasket(HashMap<String, Integer> recipeAmountMap) {
    HashMap<String, String> shoppingList = new HashMap<>();

    HashMap<String, Double> neededIngredients = getTotalOfNeededIngredients(recipeAmountMap);

    for (Map.Entry<String, Double> entry : neededIngredients.entrySet()) {
      String groceryId = entry.getKey();
      double ingredientAmount = entry.getValue();
      double inventoryAmount = inventoryController.getItemAmountById(groceryId);
      double neededAmount = ingredientAmount - inventoryAmount;

      if (neededAmount > 0) {
        Grocery grocery = groceryController.getGroceryById(groceryId);
        if (grocery == null) {
          System.out.println("Something went wrong");
        } else {
          String foodName = grocery.getName();
          shoppingList.put(foodName, neededAmount + " " + grocery.getUnit().getUnit());
        }
      }
    }
    return shoppingList;
  }

  /**
   * Aggregates the total amount of each ingredient needed based on the recipes in the basket.
   *
   * @param recipeAmountMap A map of recipe IDs to their quantities.
   * @return A HashMap with grocery IDs as keys and total needed amounts as values.
   */
  private HashMap<String, Double> getTotalOfNeededIngredients(HashMap<String, Integer> recipeAmountMap) {
    HashMap<String, Double> neededIngredients = new HashMap<>();

    for (Map.Entry<String, Integer> entry : recipeAmountMap.entrySet()) {
      String recipeId = entry.getKey();
      List<Ingredient> ingredientsForRecipe =
          recipeController.getIngredientsForRecipe(recipeId);

      for (Ingredient ingredient : ingredientsForRecipe) {
        String groceryId = ingredient.getGrocery().getId();
        double neededIngredientAmount = ingredient.getAmount() * entry.getValue();

        if (neededIngredients.containsKey(groceryId)) {
          double currentAmount = neededIngredients.get(groceryId);

          neededIngredients.remove(groceryId);
          neededIngredients.put(groceryId, neededIngredientAmount + currentAmount);
        } else {
          neededIngredients.put(groceryId, neededIngredientAmount);
        }
      }
    }
    return neededIngredients;
  }
}
