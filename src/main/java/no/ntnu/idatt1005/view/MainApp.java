package no.ntnu.idatt1005.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import no.ntnu.idatt1005.model.dao.DBConnectionProvider;

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
            topBar.setStyle("-fx-background-color: #8D6E63;");


            // Prevent tabs from closing
            tabRecipe.setClosable(false);
            tabShoppingCart.setClosable(false);
            tabFridge.setClosable(false);

            // Add tabs to TabPane
            tabPane.getTabs().addAll(tabRecipe, tabShoppingCart, tabFridge);
            tabPane.getTabs().forEach(tab -> tab.setStyle("-fx-background-color: #AED581;"));



            // Set content for Fridge tab (Placeholder for actual content)
            VBox fridgeContent = new VBox();
            fridgeContent.getChildren().addAll(tabFridge.initializeUI());
            tabFridge.setContent(fridgeContent);


            tabRecipe.setContent(tabRecipe.defaultTabCreation());


            VBox tabShoppingCartContent = new VBox();
            tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab,newTab) -> {
                if(newTab == tabShoppingCart){
                    tabShoppingCartContent.getChildren().clear();
                    tabShoppingCartContent.getChildren().addAll(tabShoppingCart.defaultTabCreation(),tabShoppingCart.allRecipes(),tabShoppingCart.createFinishShoppingButton());
                    tabShoppingCart.setContent(tabShoppingCartContent);
                }
            } );

            Button finishSHoppingButton = tabShoppingCart.createFinishShoppingButton();

            //tabShoppingCartContent.getChildren().addAll(tabShoppingCart.defaultTabCreation(),tabShoppingCart.allRecipes(),finishSHoppingButton);


            //tabShoppingCart.setContent(tabShoppingCartContent);


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
      DBConnectionProvider db = new DBConnectionProvider();

      super.init();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }



}
