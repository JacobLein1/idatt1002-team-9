package no.ntnu.idatt1005.controller;

import java.util.*;

import no.ntnu.idatt1005.GroceryInfo.Grocery;
import no.ntnu.idatt1005.RecipeInfo.Ingredient;

public class BasketController {
  //opprette en liste som heter Basket som kan inneholde recipes? Og legge til en måte å kunne legge til en og en
  //recipe i denne lista, samt fjerne.

  //trenger også en metode for å generere en handleliste her, se på hvordan dette kan løses

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
          shoppingList.put(foodName, neededAmount + " " + grocery.getUnit());
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
        String groceryId = ingredient.getGroceryId();
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
