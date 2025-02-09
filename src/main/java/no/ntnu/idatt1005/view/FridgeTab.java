package no.ntnu.idatt1005.view;


import java.util.List;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import no.ntnu.idatt1005.controller.InventoryController;


import java.util.Optional;
import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.grocery.Grocery;

/**
 * The FridgeTab class represents a tab within a user interface that allows users to manage grocery items.
 * It extends the SuperTab class, providing a specialized view that has functionalities to add, update, and remove grocery items.
 * @author Sander Berge, Therese Synnøve Rondeel
 */
public class FridgeTab extends SuperTab {
    private final InventoryController inventoryController;
    private final VBox groceryItemsBox;


    /**
     * Constructs a new FridgeTab. This constructor initializes the inventory controller, the layout for displaying grocery items,
     * and sets up the user interface with the components
     */
    public FridgeTab() {
        super("Fridge");
        this.inventoryController = new InventoryController();
        this.groceryItemsBox = new VBox(5);
        defaultTabCreation();
    }

    /**
     * Initializes the UI components for the FridgeTab. This method sets up the title, buttons for adding and removing grocery items,
     * and a scrollable area for displaying the grocery items. It also sets up event handlers for the buttons to add and remove grocery items.
     */
    @Override
    public VBox defaultTabCreation() {
        this.setClosable(false);
        VBox contentBox = new VBox(10);
        contentBox.getStyleClass().add("vBox");

        Text fridgeTitle = new Text("Fridge");
        fridgeTitle.setFont(this.getTitleFont());

        HBox buttonBox = new HBox(10);

        Button addButton = new Button("Add or update grocery");
        addButton.setOnAction(e -> addOrUpdateGrocery());
        addButton.getStyleClass().add("positiveButton");

        Button removeButton = new Button("Remove grocery");
        removeButton.setOnAction(e -> removeGrocery());
        removeButton.getStyleClass().add("negativeButton");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(groceryItemsBox);
        scrollPane.setPrefViewportHeight(800);

        buttonBox.getChildren().addAll(addButton, removeButton);
        contentBox.getChildren().addAll(fridgeTitle, buttonBox, scrollPane);


        refreshGroceryItemsBox();
        return contentBox;
    }

    /**
     * Adds a new grocery item to the inventory. This method prompts the user to enter the name and quantity of the grocery item they wish to add.
     * If the user enters invalid input, or an error occurs, an error message is displayed.
     * Otherwise, the grocery item is added to the inventory, the list of grocery items is refreshed
     * and the user is notified.
     */
    private void addOrUpdateGrocery() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Add or update grocery");
        idDialog.setHeaderText("Add or update grocery");
        idDialog.setContentText("Please enter the name of the grocery:");
        Optional<String> idResult = idDialog.showAndWait();
        if (idResult.isEmpty()) {
            showAlert("Invalid amount",
                "The input was empty", Alert.AlertType.ERROR);
            return;
        }

        TextInputDialog quantityDialog = new TextInputDialog();
        quantityDialog.setTitle("Enter new amount");
        quantityDialog.setHeaderText("Enter new amount");
        quantityDialog.setContentText("Please enter the amount for the grocery:");
        Optional<String> quantityResult = quantityDialog.showAndWait();
        if (quantityResult.isEmpty() || !quantityResult.get().matches("\\d+")) {
            showAlert("Invalid amount",
                "The amount has to be a number", Alert.AlertType.ERROR);
            return;
        }

        int quantity = Integer.parseInt(quantityResult.get());
        if (inventoryController.addOrUpdateItemToInventory(idResult.get(), quantity)) {
            showAlert("Operation successful!", "The update or addition of the grocery" +
                " was successful!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Operation unsuccessful..", "The system could not find a grocery" +
                " with the requested name", Alert.AlertType.ERROR);
        }
        refreshGroceryItemsBox();
    }

    /**
     * Method for creating and showing an alert.
     *
     * @param title The title of the alert
     * @param content The information-content for the alert
     * @param type The type of alert
     */
    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    /**
     * Creates a label for a grocery item. This method takes an Ingredient-item, representing the
     * details of a grocery item with its amount in the inventory/fridge and returns a label that
     * contains the name, quantity and unit of the item.
     *
     * @param item the item representing the grocery and its amount in the inventory
     * @return a label that contains the name, quantity and unit of the item
     */
    private Label createInventoryItem(Ingredient item) {
        Label result = new Label(item.getGrocery().getName()
                + "  " + item.getAmount() + " " + item.getGrocery().getUnit().getUnit());
        result.setFont(getSmallTextFont());
        return result;
    }

    /**
     * Method for creating a label for a grocery that is not in the inventory. The label contains
     * the name and the unit of the grocery
     *
     * @param grocery the grocery to create a label for
     * @return a label that contains the name and unit of the grocery
     */
    private Label createGroceryItem(Grocery grocery) {
        Label result = new Label(grocery.getName() + ", unit: " +
                grocery.getUnit().getUnit());
        result.setFont(getSmallTextFont());

        return result;
    }

    /**
     * Refreshes the list of grocery items displayed in the user interface. This method retrieves
     * the list of grocery items from the inventory controller and creates a label for each item.
     * The labels are then added to the groceryItemsBox layout.
     */
    private void refreshGroceryItemsBox() {
        Platform.runLater(() -> {
            groceryItemsBox.getChildren().clear();
            List<Ingredient> inventoryItems = inventoryController.getAllItemsInInventory().getInventory();
            if (inventoryItems != null) {
                for (Ingredient item : inventoryItems) {
                    Label groceryLabel = createInventoryItem(item);
                    groceryItemsBox.getChildren().addAll(groceryLabel);
                }
            }
            Text noItems = new Text("\nGroceries not in inventory");
            noItems.setFont(Font.font("Arial", FontWeight.BOLD, 17));
            groceryItemsBox.getChildren().add(noItems);
            var itemsNotInInventory = inventoryController.getGroceriesNotInInventory();
            for (Grocery grocery : itemsNotInInventory) {
                Label groceryLabel = createGroceryItem(grocery);
                groceryItemsBox.getChildren().addAll(groceryLabel);
            }

        });
    }

    /**
     * Removes a grocery item from the inventory. This method prompts the user to enter the name of the grocery item they wish to remove.
     * If the user enters invalid input, or an error occurs, an error message is displayed.
     * If not, the grocery item is removed from the inventory, the list of grocery items is refreshed
     * and the user is notified.
     */
    private void removeGrocery() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Remove grocery");
        idDialog.setHeaderText("Remove grocery");
        idDialog.setContentText("Please enter the name of the grocery you wish to remove:");
        Optional<String> idResult = idDialog.showAndWait();
        if (!idResult.isPresent()) {
            showAlert("Invalid input",
                "The input was empty.", Alert.AlertType.ERROR);
            return;
        }

        if(inventoryController.removeItemFromInventory(idResult.get())){
            showAlert("Operation successful!", "The removal of the grocery was" +
                " successful!", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Operation unsuccessful..", "The system could not find a grocery" +
                " with the requested name", Alert.AlertType.ERROR);
        }
        refreshGroceryItemsBox();
    }

}