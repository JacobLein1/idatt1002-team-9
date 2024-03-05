package no.ntnu.idatt1005;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Calculator {

    private Inventory inventory;
    private RecipeList recipeList;

    public Calculator(Inventory inventory, RecipeList recipeList) {
        this.inventory = inventory;
        this.recipeList = recipeList;
    }

    public Map<String, Integer> calculateNeededGroceries(String recipeId) {
        Map<String, Integer> neededGroceries = new HashMap<>();

        Recipe recipe = recipeList.getRecipeList().get(recipeId);
        if (recipe == null) {
            System.out.println("Recipe not found for ID: " + recipeId);
            return neededGroceries;
        }

        Map<String, Integer> ingredients = recipe.getIngredients();

        for (Map.Entry<String, Integer> ingredient : ingredients.entrySet()) {
            String itemName = ingredient.getKey();
            Integer requiredQuantity = ingredient.getValue();
            Grocery grocery = inventory.getInventory().get(itemName);

            // Calculate how many more (if any) of each ingredient is needed
            int quantityNeeded = requiredQuantity - (grocery != null ? grocery.getAmount() : 0);

            if (quantityNeeded > 0) {
                neededGroceries.put(itemName, quantityNeeded);
            }
        }

        return neededGroceries;
    }

    // Function to print needed groceries
    public void printNeededGroceries(Map<String, Integer> neededGroceries) {
        if (neededGroceries.isEmpty()) {
            System.out.println("No additional groceries needed.");
        } else {
            System.out.println("Groceries needed to buy:");
            neededGroceries.forEach((item, quantity) -> System.out.println(item + ": " + quantity));
        }
    }


    public HashMap<String, Grocery> getInventory(){
        return inventory;
    }

    
}
