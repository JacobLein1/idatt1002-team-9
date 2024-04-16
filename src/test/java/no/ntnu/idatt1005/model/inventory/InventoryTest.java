package no.ntnu.idatt1005.model.inventory;

import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;
import no.ntnu.idatt1005.model.grocery.Grocery;
import no.ntnu.idatt1005.unit.UnitsE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing Inventory
 * @version 1.0
 */
class InventoryTest {

  @Test
  @DisplayName("Test add method and get method")
  void getInventory() {
    Inventory inventory = new Inventory();
    Grocery grocery1 = new Grocery("Test1", null, "1", UnitsE.ARTICLE);
    Grocery grocery2 = new Grocery("Test2", null, "2", UnitsE.ARTICLE);
    Ingredient ingredient1 = new Ingredient(grocery1, 1);
    Ingredient ingredient2 = new Ingredient(grocery2, 2);
    inventory.addGroceryToInventory(ingredient1);
    inventory.addGroceryToInventory(ingredient2);
    assertEquals(2, inventory.getInventory().size());
    assertEquals(ingredient1, inventory.getInventory().get(0));
    assertEquals(ingredient2, inventory.getInventory().get(1));
  }
}