package no.ntnu.idatt1005.view;


import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import no.ntnu.idatt1005.controller.InventoryController;
import no.ntnu.idatt1005.Unit.UnitsE;


import java.util.Optional;


public class FridgeTab extends SuperTab {
    private InventoryController inventoryController;
    private VBox groceryItemsBox;

    public FridgeTab() {
        super("Fridge");
        this.inventoryController = new InventoryController();
        this.groceryItemsBox = new VBox(5);
        initializeUI();
    }

    public VBox initializeUI() {
        VBox contentBox = new VBox(10);

        Text fridgeTitle = new Text("Fridge");
        fridgeTitle.setFont(this.getTitleFont());

        Button addButton = new Button("Add/Update grocery");
        addButton.setOnAction(e -> addGrocery());

        Button removeButton = new Button("Remove grocery");
        removeButton.setOnAction(e -> removeGrocery());

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(groceryItemsBox);
        scrollPane.setPrefViewportHeight(800);


        contentBox.getChildren().addAll(fridgeTitle, addButton, removeButton, scrollPane);


        refreshGroceryItemsBox();
        //this.setContent(contentBox);
        return contentBox;
    }


    private void addGrocery() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Add or update grocery");
        idDialog.setHeaderText("Add or update grocery");
        idDialog.setContentText("Please enter the id of the grocery:");
        Optional<String> idResult = idDialog.showAndWait();
        if (!idResult.isPresent() || !idResult.get().matches("\\d+")) {
            showAlert("Invalid ID",
                "The ID of the grocery has to be a number.", Alert.AlertType.ERROR);
            return;
        }

        TextInputDialog quantityDialog = new TextInputDialog();
        quantityDialog.setTitle("Enter new amount");
        quantityDialog.setHeaderText("Enter new amount");
        quantityDialog.setContentText("Please enter the new/updated amount:");
        Optional<String> quantityResult = quantityDialog.showAndWait();
        if (!quantityResult.isPresent() || !quantityResult.get().matches("\\d+")) {
            showAlert("Invalid amount",
                "The amount has to be a number", Alert.AlertType.ERROR);
            return;
        }

        int quantity = Integer.parseInt(quantityResult.get());
        inventoryController.addOrUpdateItemToInventory(idResult.get(), quantity);
        refreshGroceryItemsBox();
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }



    private Label createGroceryItem(String[] groceryDetails) {
        Label nameLabel = new Label("Id: " + groceryDetails[0] + ", "+ groceryDetails[1]+ "  "
            + groceryDetails[2]);
        //nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        return nameLabel;
    }


    private void refreshGroceryItemsBox() {
        Platform.runLater(() -> {
            groceryItemsBox.getChildren().clear();
            var inventoryItems = inventoryController.getAllItemsInInventory();
            if (inventoryItems != null) {
                for (String[] item : inventoryItems) {
                    Label groceryLabel = createGroceryItem(item);
                    groceryItemsBox.getChildren().addAll(groceryLabel);
                }
            }
            groceryItemsBox.getChildren().add(new Text("\nGroceries not in inventory"));
            var itemsNotInInventory = inventoryController.getGroceriesNotInInventory();
            for (String[] item : itemsNotInInventory) {
                    Label groceryLabel = createGroceryItem(item);
                    groceryItemsBox.getChildren().addAll(groceryLabel);
            }

        });
    }


    private void removeGrocery() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Remove grocery");
        idDialog.setHeaderText("Remove grocery");
        idDialog.setContentText("Please enter the id of the grocery you wish to remove:");
        Optional<String> idResult = idDialog.showAndWait();
        if (!idResult.isPresent() || !idResult.get().matches("\\d+")) {
            showAlert("Invalid ID",
                "The ID of the grocery has to be a number.", Alert.AlertType.ERROR);
            return;
        }

        inventoryController.removeItemFromInventory(idResult.get());
        refreshGroceryItemsBox();
    }

}