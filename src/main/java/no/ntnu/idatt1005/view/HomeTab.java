package no.ntnu.idatt1005.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class HomeTab extends SuperTab {
    Font tabTitleFont = Font.font("Arial", FontWeight.BOLD, 15);
    Font tabDescriptionFont = Font.font("Arial", FontWeight.NORMAL, 15);
    public HomeTab(){
        super("Home");
    }

    /**
     * Method for creating the default tab for the home page
     */
    @Override
    public VBox defaultTabCreation(){
        this.setClosable(false);
        Text homeTitle = new Text("Home page");
        homeTitle.setFont(this.getTitleFont());
        Text homeUnderTitle = new Text("Welcome to the home page!");
        homeUnderTitle.setFont(this.getUnderTitleFont());

        Text homeDescription = new Text("The cookbook is a digital cookbook that allows you to view recipes and add them to your shopping cart. You can also manage your fridge and add groceries to it. Enjoy your stay!");
        homeDescription.setWrappingWidth(1250);
        homeDescription.setFont(this.getDescriptionFont());

        HBox recipeTab = new HBox();
        Text recipeTabText = new Text("Recipe page - ");
        recipeTabText.setFont(tabTitleFont);
        Text recipeTabDescription = new Text("The recipe tab allows you to view different recipes with their ingredients and instructions.");
        recipeTabDescription.setFont(this.tabDescriptionFont);
        recipeTab.getChildren().addAll(recipeTabText, recipeTabDescription);

        HBox shoppingCartTab = new HBox();
        Text shoppingCartTabText = new Text("Shopping cart page - ");
        shoppingCartTabText.setFont(tabTitleFont);
        Text shoppingCartTabDescription = new Text("The shopping cart tab allows you to add recipes to your shopping cart and generate an automated shopping list compared to your current inventory.");
        shoppingCartTabDescription.setFont(this.tabDescriptionFont);
        shoppingCartTab.getChildren().addAll(shoppingCartTabText, shoppingCartTabDescription);

        HBox fridgeTab = new HBox();
        Text fridgeTabText = new Text("Fridge page - ");
        fridgeTabText.setFont(tabTitleFont);
        Text fridgeTabDescription = new Text("The fridge tab allows you to view your current inventory and add, update, or remove groceries from it");
        fridgeTabDescription.setFont(this.tabDescriptionFont);
        fridgeTab.getChildren().addAll(fridgeTabText, fridgeTabDescription);

        VBox tabDescriptionBox = new VBox();
        tabDescriptionBox.getChildren().addAll(recipeTab, shoppingCartTab, fridgeTab);
        tabDescriptionBox.setPadding(new Insets(30));
        tabDescriptionBox.setSpacing(20);

        VBox homeContent = new VBox();
        homeContent.setSpacing(10);
        homeContent.setPadding(new Insets(10));
        homeContent.getChildren().addAll(homeTitle, homeUnderTitle, homeDescription, tabDescriptionBox);
        return homeContent;
    }
}
