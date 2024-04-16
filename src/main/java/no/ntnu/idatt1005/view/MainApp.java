package no.ntnu.idatt1005.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileInputStream;
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


            // Layout for the top bar (search field)
            FileInputStream inputStream = new FileInputStream("src/main/resources/cookbookLogo.jpeg");

            ImageView logo = new ImageView(new Image(inputStream));
            logo.setFitWidth(80);
            logo.setFitHeight(80);
            Text title = new Text("The CookBook");
            title.setStyle("-fx-font-size: 30px; -fx-fill: white;");

            HBox topBar = new HBox();
            topBar.setAlignment(javafx.geometry.Pos.CENTER);

            topBar.getChildren().addAll(logo,title);
            topBar.setStyle("-fx-background-color: #8D6E63;");


            // Prevent tabs from closing
            tabRecipe.setClosable(false);
            tabShoppingCart.setClosable(false);
            tabFridge.setClosable(false);

            // Add tabs to TabPane
            tabPane.getTabs().addAll(tabRecipe, tabShoppingCart, tabFridge);
            tabPane.getTabs().forEach(tab -> tab.getStyleClass().add("tab"));

            // Set content for each tab
            VBox fridgeContent = new VBox();
            fridgeContent.getChildren().addAll(tabFridge.defaultTabCreation());
            tabFridge.setContent(fridgeContent);


            tabRecipe.setContent(tabRecipe.defaultTabCreation());


            VBox tabShoppingCartContent = new VBox();

            // Add listener to tabPane to update shopping cart when tab is selected, to ensure reset of shopping cart tab when clicked
            tabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab,newTab) -> {
                if(newTab == tabShoppingCart){
                    tabShoppingCartContent.getChildren().clear();

                  tabShoppingCart.setContent(tabShoppingCart.defaultTabCreation());
                }
            } );


            // Main layout
            VBox mainLayout = new VBox(topBar,tabPane);

            // Create scene with the main layout
            Scene scene = new Scene(mainLayout, 800, 600);

            scene.getStylesheets().add("ApplicationStyling.css");

            primaryStage.setTitle("The CookBook");
            primaryStage.setScene(scene);
            primaryStage.show();
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
