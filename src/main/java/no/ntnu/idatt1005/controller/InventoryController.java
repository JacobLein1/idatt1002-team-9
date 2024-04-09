package no.ntnu.idatt1005.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import no.ntnu.idatt1005.GroceryInfo.Grocery;
import no.ntnu.idatt1005.dao.DBConnectionProvider;
import no.ntnu.idatt1005.dao.InventoryDAO;

public class InventoryController {
  InventoryDAO inventoryDAO;
  GroceryController groceryController;

  public InventoryController() {
    inventoryDAO = new InventoryDAO(DBConnectionProvider.instance());
    groceryController = new GroceryController();
  }

  //Got help from chatGPT
  public List<String[]> getAllItemsInInventory() {
    HashMap<String, Double> allInventory = inventoryDAO.getInventory();

    List<String[]> convertedInventory = new ArrayList<>();

    for (Map.Entry<String, Double> entry : allInventory.entrySet()) {
      try {
        Grocery grocery = groceryController.getGroceryById(entry.getKey());
        String groceryId = grocery.getId();
        String foodName = grocery.getName();
        Double amount = entry.getValue();
        convertedInventory.
            add(new String[] {groceryId, foodName, amount + " " + grocery.getUnit().getUnit()});
      } catch (Exception e) {
        //Add something to make the user aware that something went wrong, or that the Inventory
        //is empty
        return convertedInventory;
      }
    }

    return convertedInventory;
  }

  public void addOrUpdateItemToInventory(String id, int amount) {
    try {
      int parsedId = Integer.parseInt(id);
      double currentAmount = getItemAmountById(id);
      if (currentAmount > 0) {
        inventoryDAO.updateGrocery(parsedId, amount);
      } else {
        inventoryDAO.addGrocery(parsedId, amount);
      }

    } catch (Exception e) {
      // Instead of using system.out.println, an error message may be sent to the user, letting
      // them know something/what went wrong
      System.out.println("Something went wrong");
    }
  }

  public void removeItemFromInventory(String id) {
    try {
      int parsedId = Integer.parseInt(id);
      inventoryDAO.removeGrocery(parsedId);
    } catch (Exception e) {
      // Instead of using system.out.println, an error message may be sent to the user, letting
      // them know something/what went wrong
      System.out.println("Something went wrong");
    }
  }

  public double getItemAmountById(String id) {
    try {
      int parsedId = Integer.parseInt(id);
      return inventoryDAO.getGroceryAmount(parsedId);
    } catch (Exception e) {
      // Instead of using system.out.println, an error message may be sent to the user, letting
      // them know something/what went wrong
      System.out.println("Something went wrong");
      return 0;
    }
  }

  public List<String[]> getGroceriesNotInInventory() {
    List<Grocery> allGroceries = groceryController.getAllGroceries();
    List<String[]> groceriesNotInInventory = new ArrayList<>();

    for (Grocery grocery : allGroceries) {
      double amount = getItemAmountById(grocery.getId());
      if (amount > 0) {
        // Empty since the goal of this method is to get all groceries that don't exist in the
        // inventory, i.e. we don't want to add the groceries with an amount greater than 0
      }else {
        String groceryId = grocery.getId();
        String foodName = grocery.getName();
        groceriesNotInInventory.add(new String[]
            {groceryId, foodName, ", unit: " + grocery.getUnit().getUnit()});
      }
    }
    return groceriesNotInInventory;
  }
}
