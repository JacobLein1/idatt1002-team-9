package no.ntnu.idatt1005.model.grocery;

import no.ntnu.idatt1005.model.unit.UnitsE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class for testing Grocery
 * @version 1.0
 */
class GroceryTest {

  UnitsE u;

  @BeforeEach
  void setUp() {
    u = UnitsE.ARTICLE;
  }

  @Test
  @DisplayName("Test constructor")
  void constructor() {
    Grocery grocery = new Grocery("Test", "Test", "1", u);
    assertEquals(grocery.getClass(), Grocery.class);
  }

  @Test
  @DisplayName("Test exception in constructor")
  void constructorException() {
    assertThrows(IllegalArgumentException.class, () -> new Grocery(null, "Test", "1", u));
    assertThrows(IllegalArgumentException.class, () -> new Grocery("Test", "Test", null, u));
    assertThrows(IllegalArgumentException.class, () -> new Grocery("Test", "Test", "1", null));
  }

  @Test
  @DisplayName("Test getName method")
  void getName() {
    Grocery grocery = new Grocery("Test", null, "1", u);
    assertEquals("Test", grocery.getName());
  }

  @Test
  @DisplayName("Test getImage method")
  void getId() {
    Grocery grocery = new Grocery("Test", "Test", "1", u);
    assertEquals("Test", grocery.getImage());
  }

  @Test
  @DisplayName("Test getId method")
  void getUnit() {
    Grocery grocery = new Grocery("Test", null, "1", u);
    assertEquals("1", grocery.getId());
  }
}