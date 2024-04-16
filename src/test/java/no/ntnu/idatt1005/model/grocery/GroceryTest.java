package no.ntnu.idatt1005.model.grocery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing Grocery
 * @version 1.0
 */
class GroceryTest {

  @Test
  @DisplayName("Test getName method")
  void getName() {
    Grocery grocery = new Grocery("Test", null, "1", null);
    assertEquals("Test", grocery.getName());
  }

  @Test
  @DisplayName("Test getImage method")
  void getId() {
    Grocery grocery = new Grocery(null, "Test", "1", null);
    assertEquals("Test", grocery.getImage());
  }

  @Test
  @DisplayName("Test getId method")
  void getUnit() {
    Grocery grocery = new Grocery(null, null, "1", null);
    assertEquals("1", grocery.getId());
  }
}