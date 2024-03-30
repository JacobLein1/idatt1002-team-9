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
import no.ntnu.idatt1005.Recipe.Recipe;
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


            // Create Tabs
            RecipeTab tabRecipe = new RecipeTab();
            ShoppingCartTab tabShoppingCart = new ShoppingCartTab();
            FridgeTab tabFridge = new FridgeTab();

            // Search field at the top
            TextField searchField = new TextField();
            searchField.setPromptText("Search");
            // Layout for the top bar (search field)
            HBox topBar = new HBox(searchField);
            topBar.setStyle("-fx-background-color: #CACACA;"); // Example styling


            // Prevent tabs from closing
            tabRecipe.setClosable(false);
            tabShoppingCart.setClosable(false);
            tabFridge.setClosable(false);

            // Add tabs to TabPane
            tabPane.getTabs().addAll(tabRecipe, tabShoppingCart, tabFridge);

            // Set content for Recipe tab (Placeholder for actual content)
            VBox recipeContent = new VBox(new Label("Recipes Content"));
            Text recipeText = new Text("Recipes");
            recipeText.setFont(tabRecipe.getTitleFont());
            Text yourRecipes = new Text("Your Recipes");
            yourRecipes.setFont(tabRecipe.getUnderTitleFont());
            recipeContent.getChildren().addAll(recipeText, yourRecipes);

            // Set content for Fridge tab (Placeholder for actual content)
            VBox fridgeContent = new VBox(new Label("Fridge Content"));
            tabFridge.setContent(fridgeContent);


            tabRecipe.setContent(recipeContent);


            VBox tabShoppingCartContent = new VBox();
            Button finishSHoppingButton = tabShoppingCart.createFinishShoppingButton();

            tabShoppingCartContent.getChildren().addAll(tabShoppingCart.defaultTabCreation(),tabShoppingCart.allRecipes(),finishSHoppingButton);


            tabShoppingCart.setContent(tabShoppingCartContent);


            // Main layout
            VBox mainLayout = new VBox(topBar,tabPane);

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
