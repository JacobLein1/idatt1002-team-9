package no.ntnu.idatt1005.view;

import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import no.ntnu.idatt1005.controller.BasketController;

//This class is used to create and update the shopping cart tab
public class ShoppingCartTab extends SuperTab {
    private final Text shoppingCartTitle = new Text("Shopping Cart");
    public ShoppingCartTab() {
        super("Shopping Cart");
    }
    public void defaultTabCreation() {

        this.setClosable(false);

        VBox shoppingCartContent = new VBox();
        Text shoppingCartDescription = new Text("Please click on the desired recipes to add it to your shopping cart. The required goods to buy will be calculated later.");
        shoppingCartDescription.setFont(this.getDescriptionFont());

        shoppingCartContent.getChildren().addAll(shoppingCartTitle, shoppingCartDescription);
        shoppingCartTitle.setFont(this.getTitleFont());

        BasketController basketController = new BasketController();
        basketController.getShoppingListFromBasket();


        this.setContent(shoppingCartContent);
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
            Button resultButton = new Button("Finish shopping ->");
            resultButtonBox.getChildren().add(resultButton);
            shoppingCartContent.getChildren().add(resultButtonBox);

            //Node originalContent = tabShoppingCart.getContent();
            //Create a new scene for the shopping cart
            resultButton.setOnAction(e ->{
                tabShoppingCart.setContent(null);
                Scene shoppingCartScene = new Scene(tabPane);
                Text shoppingListText = new Text("Shopping List");
                shoppingCartContent.getChildren().addAll(shoppingCartText, shoppingListText);


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
