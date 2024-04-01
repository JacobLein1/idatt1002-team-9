package no.ntnu.idatt1005.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt1005.Recipe.Recipe;
import no.ntnu.idatt1005.controller.BasketController;
import no.ntnu.idatt1005.controller.RecipeController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//This class is used to create and update the shopping cart tab
public class ShoppingCartTab extends SuperTab {
    private final Text shoppingCartTitle = new Text("Shopping Cart");
    private final BasketController basketController = new BasketController();
    private final RecipeController recipeController = new RecipeController();
    private final HBox recipeBox = allRecipes();
    private Map<String,Integer> recipeAmountMap;

    public ShoppingCartTab() {
        super("Shopping Cart");
        recipeAmountMap = new HashMap<>();
    }
    public VBox defaultTabCreation() {

        this.setClosable(false);

        VBox shoppingCartContent = new VBox();
        shoppingCartContent.setSpacing(10);

        //Kan vurdere å legge til i superklassen
        shoppingCartContent.setPadding(new Insets(30));
        Text shoppingCartDescription = new Text("Please click on the desired recipes to add it to your shopping cart. The required goods to buy will be calculated later.");
        shoppingCartDescription.setFont(this.getDescriptionFont());

        shoppingCartContent.getChildren().addAll(shoppingCartTitle, shoppingCartDescription);
        shoppingCartTitle.setFont(this.getTitleFont());
        //basketController.getShoppingListFromBasket();


        return shoppingCartContent;
    }

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
            recipeName.setId(recipe.getRecipeID());

            Text counterText = new Text("0");
            Button decreaseButton = new Button("-");
            Button increaseButton = new Button("+");

            //Add responses to the buttons
            decreaseButton.setOnAction(e -> {
                int currentAmount = Integer.parseInt(counterText.getText());
                if (currentAmount > 0) {
                    counterText.setText(Integer.toString(currentAmount - 1));
                }
            });

            increaseButton.setOnAction(e -> {
                int currentAmount = Integer.parseInt(counterText.getText());
                counterText.setText(Integer.toString(currentAmount + 1));
            });

            HBox counterBox = new HBox(decreaseButton, counterText, increaseButton);
            counterBox.setSpacing(10);
            currentRecipeBox.getChildren().addAll(recipeName,counterBox);
            recipeBox.getChildren().add(currentRecipeBox);
        }
        recipeBox.getChildren().add(createFinishShoppingButton());
        return recipeBox;
    }

    public Button createFinishShoppingButton(){
        Button finishShoppingButton = new Button("Finish shopping ->");
        finishShoppingButton.setOnAction(e -> {
            recipeAmountMap = new HashMap<>();

            for (int i = 0; i < recipeBox.getChildren().size(); i++) {
                if (recipeBox.getChildren().get(i) instanceof VBox){
                    VBox currentRecipeBox = (VBox) recipeBox.getChildren().get(i);

                    //Finds each recipe box and gets the recipe name and the amount of the recipe
                    Node recipeNameNode = currentRecipeBox.getChildren().get(0);
                    Node counterBoxNode = currentRecipeBox.getChildren().get(1);

                    String recipeName = ((Text) recipeNameNode).getText();
                    String recipeId = recipeNameNode.getId();
                    //Gets the amount of the recipe
                    String amount = ((Text) ((HBox) counterBoxNode).getChildren().get(1)).getText();
                    recipeAmountMap.put(recipeId, Integer.parseInt(amount));
                }
            }

            StackPane finishShoppingTempPane = createFinishShoppingTempPane();


            this.setContent(finishShoppingTempPane);
        });

        return finishShoppingButton;
    }

    public StackPane createFinishShoppingTempPane(){
        StackPane finishShoppingTempPane = new StackPane();

        VBox finishShoppingContent = new VBox();
        Text shoppingListText = new Text("Shopping List:");
        finishShoppingContent.getChildren().addAll(shoppingCartTitle, shoppingListText);

        HashMap<String, String> shoppingList = basketController.
            getShoppingListFromBasket((HashMap<String, Integer>) recipeAmountMap);

        shoppingList.forEach((recipeName, amountAndUnit) -> {
            Text recipeText = new Text(recipeName + ": " + amountAndUnit);
            finishShoppingContent.getChildren().add(recipeText);
        });

        Text counterTextTest1 = new Text();
        finishShoppingTempPane.getChildren().add(finishShoppingContent);
        return finishShoppingTempPane;
    }

    public Map<String, Integer> getRecipeAmountMap() {
        return recipeAmountMap;
    }


    /*
    // Create a recipe box with text and counter
            VBox yoghurtBox = new VBox();
            Text yoghurtWFruits = new Text("Yoghurt with fruits");
            IntegerProperty yoghurtAmount = new SimpleIntegerProperty(0);
            Text counterText = new Text();
            counterText.textProperty().bind(yoghurtAmount.asString());

            //Create buttons to increase and decrease the amount of yoghurt
            Button increaseYogButton = new Button("+");
            increaseYogButton.setOnAction(e -> yoghurtAmount.set(yoghurtAmount.get() + 1));
            Button decreaseYogButton = new Button("-");
            decreaseYogButton.setOnAction( e -> {
                if (yoghurtAmount.get() > 0) {
                    yoghurtAmount.set(yoghurtAmount.get() - 1);
                }
            });

            //Add buttons to a HBox
            HBox yogButtonBox = new HBox(decreaseYogButton, counterText, increaseYogButton);
            yogButtonBox.setSpacing(10);
            yoghurtBox.setPadding(new Insets(30));

            //Add text and buttons to the yoghurtBox
            yoghurtBox.getChildren().addAll(yoghurtWFruits, yogButtonBox);
            shoppingCartContent.getChildren().add(yoghurtBox);

            //Create HBox for the result button
            HBox resultButtonBox = new HBox();
            resultButtonBox.setAlignment(Pos.BOTTOM_RIGHT);


            //Node originalContent = tabShoppingCart.getContent();
            //Create a new scene for the shopping cart



                BasketController basketController = new BasketController();

                //Get the shopping list from the basket as an observable list
                List<String[]> list = basketController.getShoppingListFromBasket();
                ObservableList<String> shoppingList = convertListToObservableList(list);
                ListView<String> shoppingListView = new ListView<>(shoppingList);
                shoppingCartContent.getChildren().add(shoppingListView);

                primaryStage.setScene(shoppingCartScene);

            });

     */

}
