package no.ntnu.idatt1005.controller;

import java.util.List;
import no.ntnu.idatt1005.Grocery.Grocery;
import no.ntnu.idatt1005.dao.DBConnectionProvider;
import no.ntnu.idatt1005.dao.GroceryDAO;

public class GroceryController {
  GroceryDAO groceryDAO;

  public GroceryController() {
    groceryDAO = new GroceryDAO(DBConnectionProvider.instance());
  }

  public List<Grocery> getAllGroceries() {
    return groceryDAO.getAllGroceries();
  }

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
