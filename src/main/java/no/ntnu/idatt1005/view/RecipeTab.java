package no.ntnu.idatt1005.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.RecipeInfo.Recipe;
import no.ntnu.idatt1005.model.RecipeInfo.RecipeController;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeTab extends SuperTab {
  RecipeController recipeController = new RecipeController();
  public RecipeTab() {
    super("Recipe");
  }
  VBox recipeContent = new VBox();
  VBox recipeLinkResult = new VBox();


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
    HBox recipeContentBox = recipesBox();

    recipeContentBox.getChildren().addAll(recipesLinkBox(),recipeLinkResult);
    recipeContent.getChildren().add(recipeContentBox);
    recipeContent.getStyleClass().add("vBox");

    return recipeContent;
  }

  public HBox recipesBox() {
    HBox recipeBox = new HBox();
    recipeBox.setSpacing(10);

    List<Recipe> recipes = recipeController.getAllRecipes();
    System.out.println(recipes.size());
    for (Recipe recipe : recipes) {
      VBox singleRecipeBox = singleRecipeBox(recipe);
      //recipeBox.getChildren().add(singleRecipeBox);
    }

    return recipeBox;
  }

  public HBox recipesLinkBox(){
    HBox recipeLinkBox = new HBox();
    recipeLinkBox.setSpacing(10);
    List<Recipe> recipes = recipeController.getAllRecipes();


    for (Recipe recipe : recipes) {
      Button recipeLink = new Button(recipe.getRecipeName());

      recipeLinkBox.getChildren().add(recipeLink);

      recipeLink.setOnAction(e -> {
        recipeLinkResult.getChildren().clear();
        recipeLinkResult.getChildren().add(singleRecipeBox(recipe));

      });

    }
    return recipeLinkBox;
  }



  public VBox singleRecipeBox(Recipe recipe){
    VBox singleRecipeBox = new VBox();
    singleRecipeBox.setSpacing(10);
    Text recipeTitle = new Text(recipe.getRecipeName());
    recipeTitle.setFont(this.getUnderTitleFont());

    //Add the list of ingredients to the single recipe box
    Text recipeIngredientsIntro = new Text("Ingredients:");
    recipeIngredientsIntro.setFont(this.getBoldDescriptionFont());

    VBox ingredientBox = new VBox();
    ingredientBox.setSpacing(10);

    List<Ingredient> ingredientList = recipeController.getIngredientsForRecipe(recipe.getRecipeID());

    ingredientList.forEach(ingredient -> {
      ingredientBox.getChildren().add(new Text(ingredient.toString()));
    });


    singleRecipeBox.getChildren().addAll(recipeTitle,recipeIngredientsIntro,ingredientBox);

    Text recipeInstructionsIntro = new Text("Instructions:");
    recipeInstructionsIntro.setFont(this.getBoldDescriptionFont());
    Text recipeInstructions = new Text(recipe.getInstructions());

    singleRecipeBox.getChildren().addAll(recipeInstructionsIntro,recipeInstructions);
    
    return singleRecipeBox;
  }
  //this.setContent(recipeContent);
}