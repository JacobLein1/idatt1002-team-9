package no.ntnu.idatt1005;

import java.util.HashMap;
import java.util.Map;

public class RecipeList {
  private final Map<String, Recipe> recipeList;

  public RecipeList () {
    this.recipeList = new HashMap<String, Recipe>();
  }
  public Map<String, Recipe> getRecipeList() {
    return recipeList;
  }

  public void addRecipe(String id, Map<String, Integer> ingredients, String instructions, int numberOfPeople) {
    Recipe recipe = new Recipe(ingredients, instructions, numberOfPeople);
    recipeList.put(id, recipe);
  }

  public void removeRecipe(String id) {
    recipeList.remove(id);
  }

}
