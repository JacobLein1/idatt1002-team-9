package no.ntnu.idatt1005.model.inventory;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;

/**
 * Class for inventory
 * @version 1.0
 */
public class Inventory {
  private final List<Ingredient> inventory;

  /**
   * Constructor for inventory
   */
  public Inventory() {
    inventory = new ArrayList<>();
  }

  /**
   * Method for getting the inventory
   * @return inventory
   */
  public List<Ingredient> getInventory() {
    return inventory;
  }

  /**
   * Method for adding grocery to inventory
   * @param invEntity grocery to add
   */
  public void addGroceryToInventory(Ingredient invEntity) {
    inventory.add(invEntity);
  }
}
