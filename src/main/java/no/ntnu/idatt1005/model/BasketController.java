package no.ntnu.idatt1005.model;

import java.util.*;

import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.RecipeInfo.RecipeController;
import no.ntnu.idatt1005.model.grocery.Grocery;
import no.ntnu.idatt1005.model.grocery.GroceryController;

public class BasketController {
  InventoryController inventoryController;
  
  GroceryController groceryController;

  RecipeController recipeController;

  public BasketController() {
    inventoryController = new InventoryController();
    groceryController = new GroceryController();
    recipeController = new RecipeController();
  }


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

  private HashMap<String, Double> getTotalOfNeededIngredients(HashMap<String, Integer> recipeAmountMap) {
    HashMap<String, Double> neededIngredients = new HashMap<>();

    for (Map.Entry<String, Integer> entry : recipeAmountMap.entrySet()) {
      String recipeId = entry.getKey();
      List<Ingredient> ingredientsForRecipe =
          recipeController.getListOfIngredientForRecipe(recipeId);

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
