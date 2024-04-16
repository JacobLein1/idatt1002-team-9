package no.ntnu.idatt1005.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt1005.model.RecipeInfo.Recipe;
import no.ntnu.idatt1005.controller.BasketController;
import no.ntnu.idatt1005.controller.InventoryController;
import no.ntnu.idatt1005.controller.RecipeController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ShoppingCartTab class represents a tab within a user interface that allows users to view recipes and add them to a shopping cart
 * and generate a shopping list based on the recipes added to the shopping cart.
 */
public class ShoppingCartTab extends SuperTab {
    //Create a title for the shopping cart, need to use it for multiple methods
    private final Text shoppingCartTitle = new Text("Shopping Cart");
    //Create a basketController object to be able to add recipes to the basket
    private final BasketController basketController = new BasketController();
    //Create a recipeController object to be able to get all recipes
    private final RecipeController recipeController = new RecipeController();
    private final InventoryController inventoryController = new InventoryController();
    //Create a map to store the recipes and the amount of each recipe
    private Map<String,Integer> recipeAmountMap;


    /**
     * Instantiates a new Shopping cart tab.
     */
    public ShoppingCartTab() {

        super("Shopping Cart");
        this.recipeAmountMap = new HashMap<>();
    }

    /**
     * Default tab creation v box.
     *
     * @return the v box
     */
    @Override
    public VBox defaultTabCreation() {

        this.setClosable(false);

        VBox shoppingCartContent = new VBox();
        shoppingCartContent.setSpacing(10);
        shoppingCartContent.getStyleClass().add("vBox");

        Text shoppingCartDescription = new Text("Please click on the desired recipes to add it to your shopping cart. The required goods to buy will be calculated later.");
        shoppingCartDescription.setFont(this.getDescriptionFont());

        HBox allRecipesBox = allRecipes();

        try {
            Button finishShopping = createFinishShoppingButton();
            shoppingCartContent.getChildren().addAll(shoppingCartTitle, shoppingCartDescription,
                    allRecipesBox, finishShopping);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }

        shoppingCartTitle.setFont(this.getTitleFont());
        basketController.getShoppingListFromBasket((HashMap<String, Integer>) recipeAmountMap);


        return shoppingCartContent;
    }

    /**
     * All recipes, amount of each recipe and buttons for increasing and decreasing the amount
     *
     * @return HBox
     */
    public HBox allRecipes(){

        HBox recipeBox = new HBox();
        recipeBox.setSpacing(10);
        recipeBox.setPadding(new Insets(30));
        List<Recipe> recipeList = recipeController.getAllRecipes();

        for (int i = 0; i < recipeList.size(); i++) {
            //Create a recipe box with text and counter
            VBox currentRecipeBox = new VBox();
            Recipe recipe = recipeList.get(i);

            //Get recipe name, set font and add to the currentRecipeBox
            Text recipeName = new Text(recipe.getRecipeName());
            recipeName.setFont(this.getUnderTitleFont());

            Text counterText = new Text("0");
            Button decreaseButton = new Button("-");
            Button increaseButton = new Button("+");

            decreaseButton.getStyleClass().add("negativeButton");
            increaseButton.getStyleClass().add("positiveButton");

            //Add responses to the buttons
            increaseButton.setOnAction(e -> {
                int currentAmount = Integer.parseInt(counterText.getText()) + 1;
                counterText.setText(Integer.toString(currentAmount));
                recipeAmountMap.put(recipe.getRecipeID(), currentAmount);
            });

            decreaseButton.setOnAction(e -> {
                int currentAmount = Integer.parseInt(counterText.getText());
                if (currentAmount > 0) {
                    currentAmount--;
                    counterText.setText(Integer.toString(currentAmount));
                    recipeAmountMap.put(recipe.getRecipeID(), currentAmount);
                }
            });

            HBox counterBox = new HBox(decreaseButton, counterText, increaseButton);
            counterBox.setSpacing(10);
            currentRecipeBox.getChildren().addAll(recipeName,counterBox);
            recipeBox.getChildren().add(currentRecipeBox);
        }
        return recipeBox;
    }

    /**
     * Create finish shopping button.
     *
     * @return Button
     */
    public Button createFinishShoppingButton() throws FileNotFoundException {

            FileInputStream inputStream = new FileInputStream("src/main/resources/shoppingCartIcon.png");
            ImageView shoppingCartImage = new ImageView(new Image(inputStream));
            shoppingCartImage.setFitHeight(20);
            shoppingCartImage.setFitWidth(30);
            Button finishShoppingButton = new Button("Finish shopping ");
            finishShoppingButton.setGraphic(shoppingCartImage);
            finishShoppingButton.setContentDisplay(ContentDisplay.LEFT);


        finishShoppingButton.setOnAction(e -> {
            StackPane finishShoppingTempPane = createFinishShoppingTempPane();
            this.setContent(finishShoppingTempPane);
        });


        return finishShoppingButton;
    }

    /**
     * Create temporary finish shopping temp pane.
     *
     * @return StackPane
     */
    public StackPane createFinishShoppingTempPane(){
        //Create a new stackpane
        StackPane finishShoppingTempPane = new StackPane();
        //Box only for the text
        VBox shoppinCartText = new VBox();
        shoppinCartText.getStyleClass().add("vBox");
        //Box to hold the shopping list and used recipes
        VBox finishShoppingContent = new VBox();
        finishShoppingContent.getStyleClass().add("vBox");
        finishShoppingContent.setSpacing(10);
        Text shoppingListText = new Text("Shopping List:");

        //Box for the shopping list
        VBox shoppingList = new VBox();
        shoppingList.setSpacing(10);

        //Box for the used recipes
        VBox usedRecipes = new VBox();
        usedRecipes.setSpacing(10);
        usedRecipes.getChildren().add(new Text("Recipes used:"));

        //Iterate through the recipeAmountMap and add the recipes to the basket

        recipeAmountMap.forEach((recipeId, amount) -> {
            if (amount > 0) { //If the amount is greater than 0, the name will be printed out
                if (recipeController.getRecipeById(recipeId) != null) {
                    String recipeName = recipeController.getRecipeById(recipeId).getRecipeName();
                    Text recipeText = new Text(recipeName + " x" + amount);
                    usedRecipes.getChildren().add(recipeText);
                }

            }
        });

        //Iterate through the shopping list and add the groceries to the shopping list
        basketController.getShoppingListFromBasket(
            (HashMap<String, Integer>) recipeAmountMap).forEach((groceryName, amountAndUnit) -> {
            Text groceryText = new Text(groceryName + ": " + amountAndUnit);
            shoppingList.getChildren().add(groceryText);
        });


        shoppinCartText.getChildren().addAll(shoppingCartTitle,shoppingListText);
        HBox listBox = new HBox();
        listBox.setSpacing(30);
        listBox.getChildren().addAll(shoppingList, usedRecipes);
        finishShoppingContent.getChildren().addAll(shoppinCartText, listBox);

        finishShoppingTempPane.getChildren().add(finishShoppingContent);
        return finishShoppingTempPane;
    }
}
