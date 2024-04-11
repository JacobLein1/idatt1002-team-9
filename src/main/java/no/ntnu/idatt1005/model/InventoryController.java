package no.ntnu.idatt1005.model;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.idatt1005.model.dao.DBConnectionProvider;
import no.ntnu.idatt1005.model.dao.InventoryDAO;
import no.ntnu.idatt1005.model.grocery.Grocery;
import no.ntnu.idatt1005.model.grocery.GroceryController;
import no.ntnu.idatt1005.model.inventory.Inventory;

public class InventoryController {
  InventoryDAO inventoryDAO;
  GroceryController groceryController;

  public InventoryController() {
    inventoryDAO = new InventoryDAO(DBConnectionProvider.instance());
    groceryController = new GroceryController();
  }

  //Got help from chatGPT
  public Inventory getAllItemsInInventory() {
      return inventoryDAO.getInventory();
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

  public List<Grocery> getGroceriesNotInInventory() {
    List<Grocery> allGroceries = groceryController.getAllGroceries();
    List<Grocery> groceriesNotInInventory = new ArrayList<>();

    for (Grocery grocery : allGroceries) {
      double amount = getItemAmountById(grocery.getId());
      if (amount > 0) {
        // Empty since the goal of this method is to get all groceries that don't exist in the
        // inventory, i.e. we don't want to add the groceries with an amount greater than 0
      }else {
        groceriesNotInInventory.add(grocery);
      }
    }
    return groceriesNotInInventory;
  }
}
