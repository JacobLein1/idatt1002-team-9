package no.ntnu.idatt1005.view;

import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import no.ntnu.idatt1005.Grocery.Grocery;
import no.ntnu.idatt1005.Grocery.GroceryList;
import no.ntnu.idatt1005.Unit.UnitsE;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
        groceryList.addGrocery(new Grocery("Melk", "melk.png", "001", UnitsE.VOLUME, 1000));
        groceryList.addGrocery(new Grocery("Brød", "brod.png", "002", UnitsE.ARTICLE, 2));
        groceryList.addGrocery(new Grocery("Ost", "ost.png", "003", UnitsE.WEIGHT, 500));
        groceryList.addGrocery(new Grocery("Egg", "egg.png", "004", UnitsE.ARTICLE, 6));
    }

    private void initializeUI() {
        VBox contentBox = new VBox(10);
        Button addButton = new Button("Legg til Grocery");
        addButton.setOnAction(e -> addGrocery());
    
        Button removeButton = new Button("Fjern Grocery");
        removeButton.setOnAction(e -> removeGrocery());
    
        contentBox.getChildren().addAll(addButton, removeButton);

        ScrollPane scrollPane = new ScrollPane(); 
        scrollPane.setContent(groceryItemsBox); 
        scrollPane.setPrefViewportHeight(800); 
    
        contentBox.getChildren().add(scrollPane);
    
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
    
        String id;
    while (true) {
        TextInputDialog idDialog = new TextInputDialog();
        idDialog.setTitle("Ny Vare ID");
        idDialog.setHeaderText("Legg til en ny vare ID");
        idDialog.setContentText("Vennligst oppgi ID:");
        Optional<String> idResult = idDialog.showAndWait();
        if (!idResult.isPresent()) return;

        id = idResult.get();
        if (!id.matches("\\d+") || groceryList.getGroceryList().containsKey(id)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "ID er allerede tatt, ikke oppgitt, eller ikke et tall.");
            alert.showAndWait();
        } else {
            break;
        }
        }
    
        ChoiceDialog<UnitsE> unitDialog = new ChoiceDialog<>(UnitsE.ARTICLE, UnitsE.values());
        unitDialog.setTitle("Velg Enhet");
        unitDialog.setHeaderText("Velg enhet for varen");
        unitDialog.setContentText("Velg enhet:");
        Optional<UnitsE> unitResult = unitDialog.showAndWait();
        if (!unitResult.isPresent()) return;
    
        String quantity;
        while (true) {
        TextInputDialog quantityDialog = new TextInputDialog();
        quantityDialog.setTitle("Ny Vare Mengde");
        quantityDialog.setHeaderText("Legg til en ny vare Mengde");
        quantityDialog.setContentText("Vennligst oppgi mengde:");
        Optional<String> quantityResult = quantityDialog.showAndWait();
        if (!quantityResult.isPresent()) return;

        quantity = quantityResult.get();
        if (!quantity.matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Mengde må være et tall.");
            alert.showAndWait();
        } else {
            break;
        }
        }
    
        Grocery newGrocery = new Grocery(nameResult.get(), "", id, unitResult.get(), Integer.parseInt(quantity));
        groceryList.addGrocery(newGrocery);
        Platform.runLater(() -> refreshGroceryItemsBox());
    }
    

    private Label createGroceryItem(Grocery grocery) {
        Label nameLabel = new Label(grocery.getName() + "  " + grocery.getQuantity()+ " " + grocery.getUnit().getUnit() + " -  id: " + grocery.getId());
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        return nameLabel;
    }
    

    private void refreshGroceryItemsBox() {
    Platform.runLater(() -> {
        groceryItemsBox.getChildren().clear(); 
        List<Grocery> sortedGroceries = new ArrayList<>(groceryList.getGroceryList().values());
        sortedGroceries.sort(Comparator.comparing(Grocery::getId));

        for (Grocery grocery : sortedGroceries) {
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
    
    

