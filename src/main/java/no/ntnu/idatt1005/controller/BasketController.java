package no.ntnu.idatt1005.controller;

import java.util.*;

import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.RecipeInfo.Recipe;
import no.ntnu.idatt1005.model.grocery.Grocery;

/**
 * The BasketController class is responsible for managing the shopping list.
 * It interacts with inventory, grocery, and recipe controllers to determine
 * the ingredients needed to purchase based on recipes and current inventory.
 * It also contains methods for accessing or modifying information in the basket.
 * GitHub Copilot assisted with methods related to calculating the ingredients needed for
 * the shopping list.
 *
 * @author Sigrid Hoel, Therese Synnøve Rondeel, Mikael Stray Frøyshov
 */
public class BasketController {
  /**
   * Field for creating an instance of an InventoryController-object
   */
  private final InventoryController inventoryController;

  /**
   * Field for creating an instance of a GroceryController-object
   */
  private final GroceryController groceryController;

  /**
   * Field for creating an instance of a RecipeController-object
   */
  private final RecipeController recipeController;

  /**
   * Field for creating a basket that has the groceryId as its key, and the current amount of
   * said recipe as its value.
   */
  private final Map<String, Integer> recipeBasket;

  /**
   * Constructor for creating an object of the BasketController class. It initializes the
   * controller classes as well as the basket of recipes.
   */
  public BasketController() {
    inventoryController = new InventoryController();
    groceryController = new GroceryController();
    recipeController = new RecipeController();
    recipeBasket = new HashMap<>();
  }

  /**
   * Method for setting the amount of a recipe in the basket. If the new amount is less than or
   * equal to zero, the recipe is removed from the basket.
   *
   * @param id the id of the recipe
   * @param currentAmountOfRecipes the current amount of the recipe
   */
  public void setAmountOfRecipeInBasket (String id, int currentAmountOfRecipes) {
    recipeBasket.put(id, currentAmountOfRecipes);
    if (recipeBasket.get(id) <= 0) {
      recipeBasket.remove(id);
    }
  }

  /**
   * Method for getting a list of all recipes in the basket.
   *
   * @return a list of all recipes in the basket
   */
  public List<Recipe> getRecipesInBasket() {
    List<Recipe> recipesInBasket = new ArrayList<>();

    for (Map.Entry<String, Integer> entry: recipeBasket.entrySet()) {
      Recipe recipe = recipeController.getRecipeById(entry.getKey());
      recipesInBasket.add(recipe);
    }
    return recipesInBasket;
  }

  /**
   * Method for getting the amount of a specific recipe in the basket based on its id.
   *
   * @param id the id of the recipe
   * @return the amount of the recipe in the basket
   */
  public int getAmountOfRecipeInBasket(String id) {
    return recipeBasket.get(id);
  }


  /**
   * Generates a shopping list from a basket of recipes with their respective amounts. The original
   * method was made with the assistance of GitHub Copilot, and has later been modified to
   * accommodate the changes in the code base.
   *
   * @return A HashMap with the Grocery as the key, and the needed amount to buy as the value
   */
  public HashMap<Grocery, Double> getShoppingListFromBasket() {
    HashMap<Grocery, Double> shoppingList = new HashMap<>();

    //gets the needed ingredients based on the amount of recipes in the basket
    HashMap<String, Double> neededIngredients = getTotalOfNeededIngredients();

    //for each entry in the needed ingredient, the amount the user needs to buy is calculated
    for (Map.Entry<String, Double> entry : neededIngredients.entrySet()) {
      String groceryId = entry.getKey();
      double ingredientAmount = entry.getValue();
      double inventoryAmount = inventoryController.getItemAmountById(groceryId);
      double neededAmount = ingredientAmount - inventoryAmount;

      //if the needed amount is greater than 0, the grocery is added to the shoppinglist
      if (neededAmount > 0) {
        Grocery grocery = groceryController.getGroceryById(groceryId);
        shoppingList.put(grocery, neededAmount);
      }
    }
    return shoppingList;
  }

  /**
   * Aggregates the total amount of each ingredient needed based on the recipes in the basket. The
   * original method was made with the assistance of GitHub Copilot, and has later been modified to
   * accommodate the changes in the code base.
   *
   * @return A HashMap with grocery IDs as keys and total required amounts as values.
   */
  private HashMap<String, Double> getTotalOfNeededIngredients() {
    HashMap<String, Double> requiredIngredients = new HashMap<>();

    //For each recipe in the basket the ingredients required for each recipe is calculated
    for (Map.Entry<String, Integer> entry : recipeBasket.entrySet()) {
      String recipeId = entry.getKey();
      Recipe recipe = recipeController.getRecipeById(recipeId);
      List<Ingredient> ingredientsForRecipe = recipe.getIngredients();

      for (Ingredient ingredient : ingredientsForRecipe) {
        String groceryId = ingredient.getGrocery().getId();
        //the required ingredients are calculated based on what the recipe requires, as well
        //as how many of the specific recipe has been added to the basket
        double requiredIngredientAmount = ingredient.getAmount() * entry.getValue();

        //It checks whether the grocery already exists in the map, if it does, the old value is
        //"saved", and the grocery is added back with the old value and the new value. If not
        //the grocery may be added without problem
        if (requiredIngredients.containsKey(groceryId)) {
          double currentAmount = requiredIngredients.get(groceryId);

          requiredIngredients.remove(groceryId);
          requiredIngredients.put(groceryId, requiredIngredientAmount + currentAmount);
        } else {
          requiredIngredients.put(groceryId, requiredIngredientAmount);
        }
      }
    }
    return requiredIngredients;
  }
}
