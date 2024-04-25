package no.ntnu.idatt1005.view;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.RecipeInfo.Recipe;
import no.ntnu.idatt1005.controller.RecipeController;

import java.util.List;

/**
 * RecipeTab class represents a tab within a user interface that allows users to view recipes.
 */
public class RecipeTab extends SuperTab {
  RecipeController recipeController = new RecipeController();
  public RecipeTab() {
    super("Recipe");
  }
  VBox recipeContent = new VBox();
  VBox recipeLinkResult = new VBox();


  /**
   * Creates a VBox with the default content for the RecipeTab. This method sets up the title and a list of all recipes.
   *
   * @return v box
   */
  @Override
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
    HBox recipeContentBox = new HBox();
    recipeContentBox.setSpacing(40);

    recipeContentBox.getChildren().addAll(recipesLinkBox(),recipeLinkResult);
    recipeContent.getChildren().add(recipeContentBox);
    recipeContent.getStyleClass().add("vBox");

    return recipeContent;
  }

  /**
   * Creates HBox with all recipes as buttons. Each button has action response and will display the recipe when clicked.
   * @return HBox with all recipes
   */
  public VBox recipesLinkBox(){
    VBox recipeLinkBox = new VBox();
    recipeLinkBox.setSpacing(10);
    List<Recipe> recipes = recipeController.getAllRecipes();


    for (Recipe recipe : recipes) {
      //Button recipeLink = new Button(recipe.getRecipeName());
      Text recipeLink = new Text(recipe.getRecipeName());
      recipeLink.setFont(getSmallTextFont());
      recipeLink.getStyleClass().add("recipeText");

      recipeLinkBox.getChildren().add(recipeLink);

      recipeLink.setOnMouseClicked(e -> {
        recipeLinkResult.getChildren().clear();
        recipeLinkResult.getChildren().add(singleRecipeBox(recipe));
      });
    }
    return recipeLinkBox;
  }


  /**
   * Creates a VBox with a single recipe visualized in UI components. Including the recipe name, ingredients, and instructions.
   *
   * @param recipe the recipe to be visualized
   * @return VBox with a single recipe visualized in UI components
   */
  public VBox singleRecipeBox(Recipe recipe){
    VBox singleRecipeBox = new VBox();
    singleRecipeBox.setSpacing(10);
    Text recipeTitle = new Text(recipe.getRecipeName());
    recipeTitle.setFont(this.getUnderTitleFont());

    Text amountOfPeople = new Text("For: " + recipe.getNumberOfPeople() + " people");
    amountOfPeople.setFont(getSmallTextFont());

    //Add the list of ingredients to the single recipe box
    Text recipeIngredientsIntro = new Text("Ingredients:");
    recipeIngredientsIntro.setFont(this.getBoldDescriptionFont());

    VBox ingredientBox = new VBox();
    ingredientBox.setSpacing(10);

    List<Ingredient> ingredientList = recipe.getIngredients();

    ingredientList.forEach(ingredient -> {
      Text currentIngredientText = new Text(ingredient.toString());
      currentIngredientText.setFont(getSmallTextFont());
      ingredientBox.getChildren().add(currentIngredientText);
    });

    Text recipeInstructionsIntro = new Text("Instructions:");
    recipeInstructionsIntro.setFont(this.getBoldDescriptionFont());
    Text recipeInstructions = new Text(recipe.getInstructions());
    recipeInstructions.setFont(getSmallTextFont());

    singleRecipeBox.getChildren().addAll(recipeTitle, amountOfPeople, recipeIngredientsIntro,
        ingredientBox, recipeInstructionsIntro, recipeInstructions);

    return singleRecipeBox;
  }
}