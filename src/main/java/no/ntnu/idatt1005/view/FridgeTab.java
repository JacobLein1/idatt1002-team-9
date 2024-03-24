package no.ntnu.idatt1005.view;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import no.ntnu.idatt1005.Grocery.Grocery;
import no.ntnu.idatt1005.Grocery.GroceryList;
import no.ntnu.idatt1005.Unit.UnitsE;

import java.util.Optional;

public class FridgeTab extends SuperTab {
    private GroceryList groceryList;
    private VBox groceryItemsBox;

    public FridgeTab() {
        super("Fridge");
        this.groceryList = new GroceryList();
        this.groceryItemsBox = new VBox(5);
        initializeTestData();
        initializeUI();
    }

    private void initializeTestData() {
        groceryList.addGrocery(new Grocery("Melk", "melk.png", "001", UnitsE.VOLUME));
        groceryList.addGrocery(new Grocery("Brød", "brod.png", "002", UnitsE.ARTICLE));
        groceryList.addGrocery(new Grocery("Ost", "ost.png", "003", UnitsE.WEIGHT));
        groceryList.addGrocery(new Grocery("Egg", "egg.png", "004", UnitsE.ARTICLE));
    }

    private void initializeUI() {
        VBox contentBox = new VBox(10);
        Button addButton = new Button("Legg til Grocery");
        addButton.setOnAction(e -> addGrocery());

    
        Button removeButton = new Button("Fjern Grocery");
        removeButton.setOnAction(e -> removeGrocery());
    
        contentBox.getChildren().addAll(addButton, removeButton, groceryItemsBox);
        refreshGroceryItemsBox();
        this.setContent(contentBox);
    }

    private void addGrocery() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Ny Vare");
        nameDialog.setHeaderText("Legg til en ny vare");
        nameDialog.setContentText("Vennligst oppgi navn:");
        Optional<String> nameResult = nameDialog.showAndWait();
        if (!nameResult.isPresent()) return;
    
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Ny Vare ID");
        idDialog.setHeaderText("Legg til en ny vare ID");
        idDialog.setContentText("Vennligst oppgi ID:");
        Optional<String> idResult = idDialog.showAndWait();
        if (!idResult.isPresent() || groceryList.getGroceryList().containsKey(idResult.get())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID er allerede tatt eller ikke oppgitt.");
            alert.showAndWait();
            return; 
        }
    
        ChoiceDialog<UnitsE> unitDialog = new ChoiceDialog<>(UnitsE.ARTICLE, UnitsE.values());
        unitDialog.setTitle("Velg Enhet");
        unitDialog.setHeaderText("Velg enhet for varen");
        unitDialog.setContentText("Velg enhet:");
        Optional<UnitsE> unitResult = unitDialog.showAndWait();
        if (!unitResult.isPresent()) return;
    
        Grocery newGrocery = new Grocery(nameResult.get(), "", idResult.get(), unitResult.get());
        groceryList.addGrocery(newGrocery);
        refreshGroceryItemsBox();
    }
    

    private Label createGroceryItem(Grocery grocery) {
        Label nameLabel = new Label(grocery.getName() + "  " + grocery.getUnit().getUnit() + "  " + grocery.getId());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        return nameLabel;
    }
    

    private void refreshGroceryItemsBox() {
        Platform.runLater(() -> {
            groceryItemsBox.getChildren().clear(); 
            for (Grocery grocery : groceryList.getGroceryList().values()) {
                Label groceryLabel = createGroceryItem(grocery); 
                groceryItemsBox.getChildren().add(groceryLabel); 
            }
        });
    }

    private void removeGrocery() {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Fjern Vare");
        idDialog.setHeaderText("Fjern en vare med ID");
        idDialog.setContentText("Vennligst oppgi ID til varen du vil fjerne:");
        Optional<String> idResult = idDialog.showAndWait();
    
        idResult.ifPresent(id -> {
            if (groceryList.getGroceryList().containsKey(id)) {
                groceryList.getGroceryList().remove(id);
                refreshGroceryItemsBox();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Ingen vare med denne ID-en ble funnet.");
                alert.showAndWait();
            }
        });
    }
    
    
}
    
    

