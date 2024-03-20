package no.ntnu.idatt1005.controller;

import java.util.*;

import no.ntnu.idatt1005.Grocery.Grocery;
import no.ntnu.idatt1005.Recipe.Recipe;

public class BasketController {
  //opprette en liste som heter Basket som kan inneholde recipes? Og legge til en måte å kunne legge til en og en
  //recipe i denne lista, samt fjerne.

  //trenger også en metode for å generere en handleliste her, se på hvordan dette kan løses

  List<Recipe> basketOfRecipes;
  
  InventoryController inventoryController;
  
  GroceryController groceryController;

  public BasketController() {
    basketOfRecipes = new ArrayList<>();
    inventoryController = new InventoryController();
    groceryController = new GroceryController();
  }

  public void addRecipeToBasket(Recipe recipe) {
    basketOfRecipes.add(recipe);
  }

  public void removeRecipeFromBasket(Recipe recipe) {
    Iterator<Recipe> iterator = basketOfRecipes.iterator();
    String recipeId = recipe.getRecipeID();
    while (iterator.hasNext()) {
      Recipe currentRecipe = iterator.next();
      String currentId = currentRecipe.getRecipeID();
      if (currentId.equals(recipeId)) {
        iterator.remove();
      }
    }
  }

  public List<String[]> getShoppingListFromBasket() {
    List<String []> shoppingList = new ArrayList<>();

    final HashMap<String, Double> neededIngredients = getTotalOfNeededIngredients();

    for (Map.Entry<String, Double> entry : neededIngredients.entrySet()) {
      String groceryId = entry.getKey();
      double ingredientAmount = entry.getValue();
      double inventoryAmount = inventoryController.getItemAmountById(groceryId);

      double neededAmount = ingredientAmount - inventoryAmount;

      if (neededAmount > 0) {
        Grocery grocery = groceryController.getGroceryById(groceryId);
        String foodName = grocery.getName();
        shoppingList.add(new String[] {foodName, neededAmount + " " + grocery.getUnit()});
      }
    }
    return shoppingList;
  }

  private HashMap<String, Double> getTotalOfNeededIngredients() {
    HashMap<String, Double> neededIngredients = new HashMap<>();

    for (Recipe recipe : basketOfRecipes) {
      for (Map.Entry<String, Double> entry : recipe.getIngredients().entrySet()) {
          String groceryId = entry.getKey();
          double ingredientAmount = entry.getValue();

          if (neededIngredients.containsKey(groceryId)) {
            double currentAmount = neededIngredients.get(groceryId);
            neededIngredients.remove(groceryId);
            neededIngredients.put(groceryId, currentAmount + ingredientAmount);
          } else {
            neededIngredients.put(groceryId, ingredientAmount);
          }
      }
    }
    return neededIngredients;
  }
}
