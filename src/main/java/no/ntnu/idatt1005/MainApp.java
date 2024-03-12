package no.ntnu.idatt1005;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
            // TabPane for Recipe, Shopping cart, and Fridge tabs
            TabPane tabPane = new TabPane();

            // Create Tabs
            Tab tabRecipe = new Tab("Recipe");
            Tab tabShoppingCart = new Tab("Shopping cart");
            Tab tabFridge = new Tab("Fridge");

            // Prevent tabs from closing
            tabRecipe.setClosable(false);
            tabShoppingCart.setClosable(false);
            tabFridge.setClosable(false);

            // Add tabs to TabPane
            tabPane.getTabs().addAll(tabRecipe, tabShoppingCart, tabFridge);

            // Set content for Recipe tab (Placeholder for actual content)
            VBox recipeContent = new VBox(new Label("Recipes Content"));
            tabRecipe.setContent(recipeContent);

            // Set content for Shopping Cart tab (Placeholder for actual content)
            VBox shoppingCartContent = new VBox(new Label("Shopping Cart Content"));
            tabShoppingCart.setContent(shoppingCartContent);

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
            VBox mainLayout = new VBox(topBar, tabPane);

            // Create scene with the main layout
            Scene scene = new Scene(mainLayout, 800, 600);

            primaryStage.setTitle("The CookBook");
            primaryStage.setScene(scene);
            primaryStage.show();
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
