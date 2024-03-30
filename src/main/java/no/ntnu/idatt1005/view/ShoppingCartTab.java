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
        this.recipeAmountMap = new HashMap<>();
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
        basketController.getShoppingListFromBasket();


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

            Text counterText = new Text("0");
            Button decreaseButton = new Button("-");
            Button increaseButton = new Button("+");

            //Add responses to the buttons
            increaseButton.setOnAction(e -> {
                int currentAmount = Integer.parseInt(counterText.getText()) + 1;
                counterText.setText(Integer.toString(currentAmount));
                recipeAmountMap.put(recipe.getRecipeName(), currentAmount);
                System.out.println(recipeAmountMap);
            });

            decreaseButton.setOnAction(e -> {
                int currentAmount = Integer.parseInt(counterText.getText());
                if (currentAmount > 0) {
                    currentAmount--;
                    counterText.setText(Integer.toString(currentAmount));
                    recipeAmountMap.put(recipe.getRecipeName(), currentAmount);
                    System.out.println(recipeAmountMap);
                }
            });

            HBox counterBox = new HBox(decreaseButton, counterText, increaseButton);
            counterBox.setSpacing(10);
            currentRecipeBox.getChildren().addAll(recipeName,counterBox);
            recipeBox.getChildren().add(currentRecipeBox);
        }
        return recipeBox;
    }

    public Button createFinishShoppingButton(){
        Button finishShoppingButton = new Button("Finish shopping ->");
        finishShoppingButton.setOnAction(e -> {
            System.out.println(recipeAmountMap);
            StackPane finishShoppingTempPane = createFinishShoppingTempPane();
            this.setContent(finishShoppingTempPane);
        });

        return finishShoppingButton;
    }

    //Lager midlertidig finishShoppingPane som skal vise hva som skal handles og hvilke retter dette kommer fra
    public StackPane createFinishShoppingTempPane(){
        //Create a new stackpane
        StackPane finishShoppingTempPane = new StackPane();
        //Box only for the text
        VBox shoppinCartText = new VBox();
        //Box to hold the shopping list and used recipes
        VBox finishShoppingContent = new VBox();
        Text shoppingListText = new Text("Shopping List:");
        //Box for the shopping list
        VBox shoppingList = new VBox();
        //Box for the used recipes
        VBox usedRecipes = new VBox();

        //Iterate through the recipeAmountMap and add the recipes to the basket
        recipeAmountMap.forEach((recipeName, amount) -> {
            if (amount > 0) { //If the amount is greater than 0, add the recipe to the basket
                Text recipeText = new Text(recipeName + ": " + amount);
                usedRecipes.getChildren().add(recipeText);

                int finalAmount = amount;
                //Add the recipe to the basket, as many times as the amount
                for (int i = 0; i < amount; i++) {
                    recipeController.getAllRecipes().forEach(recipe -> {
                        if (recipe.getRecipeName().equals(recipeName)) {
                            basketController.addRecipeToBasket(recipe);
                        }
                    });
                }

            }
        });
        System.out.println(basketController.getBasketOfRecipes().size());
        basketController.getShoppingListFromBasket().forEach(ingredient -> {
            Text ingredientText = new Text(ingredient[0] + ": " + ingredient[1]);
            System.out.println(ingredient[0] + ": " + ingredient[1]);
            shoppingList.getChildren().add(ingredientText);
        });

        shoppinCartText.getChildren().addAll(shoppingCartTitle,shoppingListText);
        HBox listBox = new HBox();
        listBox.getChildren().addAll(shoppingList, usedRecipes);
        finishShoppingContent.getChildren().addAll(shoppinCartText, listBox);

        finishShoppingTempPane.getChildren().add(finishShoppingContent);
        return finishShoppingTempPane;
    }

    public Map<String, Integer> getRecipeAmountMap() {
        return recipeAmountMap;
    }
    public VBox getNeededGoods(){

        return null;
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
