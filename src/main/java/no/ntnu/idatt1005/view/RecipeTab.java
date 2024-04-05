package no.ntnu.idatt1005.view;

import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt1005.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.RecipeInfo.Recipe;
import no.ntnu.idatt1005.controller.RecipeController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecipeTab extends SuperTab {
  RecipeController recipeController = new RecipeController();
  public RecipeTab() {
    super("Recipe");
  }
  VBox recipeContent = new VBox();


  //Create the default tab for the recipe tab
  public VBox defaultTabCreation() {
    this.setClosable(false);
    Text recipeTitle = new Text("Recipe");
    Text recipeUnderTitle = new Text("All Recipes:");
    recipeTitle.setFont(this.getTitleFont());
    recipeUnderTitle.setFont(this.getUnderTitleFont());
    recipeTitle.setFont(this.getTitleFont());

    recipeContent.getChildren().addAll(recipeTitle);

    recipeContent.setSpacing(10);
    recipeContent.getChildren().add(recipeUnderTitle);
    recipeContent.getChildren().add(recipesBox());
    return recipeContent;
  }

  public HBox recipesBox() {
    HBox recipeBox = new HBox();
    recipeBox.setSpacing(10);

    List<Recipe> recipes = recipeController.getAllRecipes();
    System.out.println(recipes.size());
    for (Recipe recipe : recipes) {
      VBox singleRecipeBox = singleRecipeBox(recipe);
      recipeBox.getChildren().add(singleRecipeBox);
    }

    return recipeBox;
  }

  public VBox singleRecipeBox(Recipe recipe){
    VBox singleRecipeBox = new VBox();
    singleRecipeBox.setSpacing(10);
    Text recipeTitle = new Text(recipe.getRecipeName());
    recipeTitle.setFont(this.getUnderTitleFont());

    //Add the list of ingredients to the single recipe box
    Text recipeIngredientsIntro = new Text("Ingredients:");
    List<Ingredient> listOfIngredients = recipeController.getListOfIngredientForRecipe(recipe.getRecipeID());

    VBox ingredientBox = new VBox();
    ingredientBox.setSpacing(10);

    HashMap<String,String> groceryList = recipeController.getIngredientsForRecipe(recipe.getRecipeID());

    for (Map.Entry<String, String> entry : groceryList.entrySet()) {
      String key = entry.getKey(); // Ingredient name
      String value = entry.getValue(); // Ingredient amount and unit

      //Add each ingredient to ingredientBox
      ingredientBox.getChildren().add(new Text(key + ": " + value));
    }


    singleRecipeBox.getChildren().addAll(recipeTitle,recipeIngredientsIntro,ingredientBox);

    Text recipeInstructionsIntro = new Text("Instructions:");
    recipeInstructionsIntro.setFont(this.getUnderTitleFont());
    Text recipeInstructions = new Text(recipe.getInstructions());

    singleRecipeBox.getChildren().addAll(recipeInstructionsIntro,recipeInstructions);

    singleRecipeBox.getChildren().addAll(recipeTitle,recipeIngredientsIntro);
    return singleRecipeBox;
  }
  //this.setContent(recipeContent);
}