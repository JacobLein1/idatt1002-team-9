package no.ntnu.idatt1005.RecipeInfo;

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

  public void addRecipe(String id, String recipeName, String instructions,
                        int numberOfPeople) throws IllegalArgumentException {
    if (recipeList.containsKey(id)) {
      throw new IllegalArgumentException("There already exists a recipe with the requested id");
    }
    Recipe recipe = new Recipe(id, recipeName, instructions, numberOfPeople);
    recipeList.put(id, recipe);
  }

  public void removeRecipe(String id) {
    if (!recipeList.containsKey(id)) {
      throw new IllegalArgumentException("There doesn't exist a recipe with the existing id");
    }
    recipeList.remove(id);
  }

}
