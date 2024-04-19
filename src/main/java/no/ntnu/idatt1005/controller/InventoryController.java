package no.ntnu.idatt1005.controller;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.dao.DBConnectionProvider;
import no.ntnu.idatt1005.model.dao.InventoryDAO;
import no.ntnu.idatt1005.model.grocery.Grocery;
import no.ntnu.idatt1005.model.inventory.Inventory;

/**
 * Class for controlling the functionalities related to the users' inventory in the database. It
 * has methods for accessing all items in the inventory, adding or updating an item,
 * removing an item, getting the amount of a grocery in the inventory and a method for getting
 * all groceries that are not currently in the inventory.
 *
 * @author Sigrid Hoel, Therese Synnøve Rondeel
 */
public class InventoryController {
  /**
   * Field for creating an instance of an InventoryDAO-object.
   */
  InventoryDAO inventoryDAO;

  /**
   * Field for creating an instance of a GroceryController-object
   */
  GroceryController groceryController;

  /**
   * Constructor for creating an object of the InventoryController class. It initializes the
   * InventoryDAO-object by calling the static instance method of the DBConnectionProvider-class.
   * It also initializes the groceryController-field.
   */
  public InventoryController() {
    inventoryDAO = new InventoryDAO(DBConnectionProvider.instance());
    groceryController = new GroceryController();
  }

  /**
   * Method for getting all items in the Inventory
   *
   * @return an object of the Inventory class, containing the inventory of the user
   */
  public Inventory getAllItemsInInventory() {
      return inventoryDAO.getInventory();
    }

  /**
   * Method for either adding or updating an item in the inventory. It gets all the groceries that
   * exist in the database, to control whether the application supports the requested grocery or
   * not. If the grocery is found, it checks if it exists in the inventory-table in the database
   * or not. If it does, the update-method is called, if not, the add-method is called.
   *
   * @param groceryName the name of the grocery
   * @param amount the amount for the grocery
   * @return true or false depending on if the addition or update of the grocery was successfully
   *         or not
   */
  public boolean addOrUpdateItemToInventory(String groceryName, int amount) {
    try {
      List<Grocery> allGroceries = groceryController.getAllGroceries();
      for (Grocery grocery : allGroceries) {
        if(grocery.getName().equalsIgnoreCase(groceryName)) {
          int parsedId = Integer.parseInt(grocery.getId());
          double currentAmount = getItemAmountById(grocery.getId());

          if (currentAmount > 0) {
            inventoryDAO.updateGrocery(parsedId, amount);
          } else {
            inventoryDAO.addGrocery(parsedId, amount);
          }
          return true;
        }
      }
    } catch (Exception e) {
      // Instead of using system.out.println, an error message may be sent to the user, letting
      // them know something/what went wrong
      System.out.println("Something went wrong");
    }
    return false;
  }

  /**
   * Method for removing an item from inventory. It gets all the groceries in the inventory to
   * control whether the grocery exists in the users' inventory. If the grocery is found, it removes
   * the grocery from the inventory.
   *
   * @param groceryName the name of the grocery
   * @return true if the removal of the grocery for successful, and false if not
   */
  public boolean removeItemFromInventory(String groceryName) {
    try {
      List<Ingredient> allGroceries = getAllItemsInInventory().getInventory();
      for (Ingredient item : allGroceries) {
        if (item.getGrocery().getName().equalsIgnoreCase(groceryName)) {
          int parsedId = Integer.parseInt(item.getGrocery().getId());
          inventoryDAO.removeGrocery(parsedId);
          return true;
        }
      }
    } catch (Exception e) {
      // Instead of using system.out.println, an error message may be sent to the user, letting
      // them know something/what went wrong
      System.out.println("Something went wrong");
    }
    return false;
  }

  /**
   * Method for getting the amount of a specific grocery by its id.
   *
   * @param id the id of the grocery
   * @return the amount of the grocery, 0 if it could not be found or if an error occurs
   */
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

  /**
   * Method for getting all groceries that are not in the Inventory.
   *
   * @return list of the groceries that are not in the inventory
   */
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
