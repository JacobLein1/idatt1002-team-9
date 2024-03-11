package no.ntnu.idatt1005;

import java.util.HashMap;
import java.util.Map;

public class Calculator {

    public static HashMap<String, Double> neededGroceries(Recipe recipe, Inventory inventory) {
        HashMap<String, Double> missingIngredients = new HashMap<>();

        HashMap<String, Grocery> inventoryMap = inventory.getInventory();
        Map<String, Integer> recipeIngredients = recipe.getIngredients();

        for (Map.Entry<String, Integer> recipeEntry : recipeIngredients.entrySet()) {
            String grocery = recipeEntry.getKey();
            double requiredAmount = recipeEntry.getValue();

            if (inventoryMap.containsKey(grocery)) {
                float inventoryAmount = inventoryMap.get(grocery).getAmount();

                if (inventoryAmount < requiredAmount) {
                    missingIngredients.put(grocery, requiredAmount - (double) inventoryAmount);
                }
            } else {
                missingIngredients.put(grocery, requiredAmount);
            }
        }
        return missingIngredients;
    }
}
