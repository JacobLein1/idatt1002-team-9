package no.ntnu.idatt1005.view;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import no.ntnu.idatt1005.controller.BasketController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
            // TabPane for Recipe, Shopping cart, and Fridge tabs
            TabPane tabPane = new TabPane();

            Font titleFont = Font.font("Arial", FontWeight.BOLD, 20);
            Font underTitleFont = Font.font("Arial", FontWeight.BOLD, 15);
            Font descriptionFont = Font.font("Arial", FontWeight.NORMAL, 12);

            // Create Tabs
            Tab tabRecipe = new Tab("Recipe");
            Tab tabShoppingCart = new Tab("Shopping cart");
            Tab tabFridge = new Tab("Fridge");

            String logoPath = "/Users/jacoblein/Desktop/Systemutvikling/cookbookLogo.jpeg";
            //Initialise logo as image, with a format

            //Creating imageObject
            InputStream stream = new FileInputStream(logoPath);
            Image logo = new Image(stream);

            //Creating image view
            ImageView logoView = new ImageView(logo);
            logoView.setImage(logo);

            //Setting the position of the image, as well as size
            logoView.setX(50);
            logoView.setY(50);
            logoView.setFitWidth(200);
            logoView.setFitHeight(200);
            logoView.setPreserveRatio(true);

            HBox logoBox = new HBox(logoView);

            // Prevent tabs from closing
            tabRecipe.setClosable(false);
            tabShoppingCart.setClosable(false);
            tabFridge.setClosable(false);

            // Add tabs to TabPane
            tabPane.getTabs().addAll(tabRecipe, tabShoppingCart, tabFridge);

            // Set content for Recipe tab (Placeholder for actual content)
            VBox recipeContent = new VBox(new Label("Recipes Content"));
            Text recipeText = new Text("Recipes");
            recipeText.setFont(titleFont);
            Text yourRecipes = new Text("Your Recipes");
            yourRecipes.setFont(underTitleFont);

            recipeContent.getChildren().addAll(recipeText, yourRecipes);


            recipeContent.getChildren().add(logoBox);
            tabRecipe.setContent(recipeContent);

            // Set content for Shopping Cart tab (Placeholder for actual content)
            VBox shoppingCartContent = new VBox();
            tabShoppingCart.setContent(shoppingCartContent);
            Text shoppingCartText = new Text("Shopping Cart");
            shoppingCartText.setFont(titleFont);
            Text shoppingCartDescription = new Text("Please click on the desired recipes to add it to your shopping cart. The required goods to buy will be calculated later.");
            shoppingCartDescription.setFont(descriptionFont);
            shoppingCartContent.getChildren().addAll(shoppingCartText, shoppingCartDescription);


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





        // Set content for Fridge tab (Placeholder for actual content)
            VBox fridgeContent = new VBox(new Label("Fridge Content"));
            tabFridge.setContent(fridgeContent);

            // Search field at the top
            TextField searchField = new TextField();
            searchField.setPromptText("Search");

            // Layout for the top bar (search field)
            HBox topBar = new HBox(searchField);
            topBar.setStyle("-fx-background-color: #CACACA;"); // Example styling



            // Main layout
            VBox mainLayout = new VBox(topBar, logoBox ,tabPane);

            // Create scene with the main layout
            Scene scene = new Scene(mainLayout, 800, 600);

            primaryStage.setTitle("The CookBook");
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    /*@Param - list: List<String[]>
    * @return - ObservableList<String>
    * This method takes a list of String arrays and converts it to an ObservableList
    * */
    public ObservableList<String> convertListToObservableList(List<String[]> list) {
    return list.stream().flatMap(Arrays::stream)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }



}
