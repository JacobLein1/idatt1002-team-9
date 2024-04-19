package no.ntnu.idatt1005.controller;

import java.util.List;

import no.ntnu.idatt1005.model.dao.DBConnectionProvider;
import no.ntnu.idatt1005.model.dao.GroceryDAO;
import no.ntnu.idatt1005.model.grocery.Grocery;

/**
 * Class for controlling the functionalities related to the groceries in the database. It has
 * methods for getting all groceries, as well as a specific grocery. GitHub Copilot assisted
 * with writing the code more quickly.
 *
 * @author Sigrid Hoel, Therese Synnøve Rondeel
 */
public class GroceryController {
  /**
   * Field for creating an instance of a GroceryDAO-object.
   */
  GroceryDAO groceryDAO;

  /**
   * Constructor for creating an object of the GroceryController class. It initializes the
   * GroceryDAO-object by calling the static instance method of the DBConnectionProvider-class.
   */
  public GroceryController() {
    groceryDAO = new GroceryDAO(DBConnectionProvider.instance());
  }

  /**
   * Method for getting all groceries in the database.
   *
   * @return a list of all groceries, or an empty list if an error occurred in the GroceryDAO-class
   */
  public List<Grocery> getAllGroceries() {
    return groceryDAO.getAllGroceries();
  }

  /**
   * Method for getting a grocery from the database by its id. The id is parsed to an int-value
   * before attempting to retrieve the grocery.
   *
   * @param id the id of the grocery
   * @return the requested grocery if found, null if not
   * @see GroceryDAO#getGroceryById(int) 
   */
  public Grocery getGroceryById(String id) {
    try {
      int parsedId = Integer.parseInt(id);
      return groceryDAO.getGroceryById(parsedId);
    } catch (Exception e) {
      // Instead of using system.out.println, an error message may be sent to the user, letting
      // them know something/what went wrong
      System.out.println("Something went wrong");
      return null;
    }
  }
}
