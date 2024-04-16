package no.ntnu.idatt1005.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitsETest {

  @Test
  @DisplayName("Test getUnit method")
  void getUnit() {
    assertEquals("stk", UnitsE.ARTICLE.getUnit());
    assertEquals("g", UnitsE.WEIGHT.getUnit());
    assertEquals("ml", UnitsE.VOLUME.getUnit());
  }

  @Test
  @DisplayName("Test getValue method")
  void getUnitValue() {
    assertEquals("ARTICLE", String.valueOf(UnitsE.getValue("stk")));
    assertEquals("WEIGHT", String.valueOf(UnitsE.getValue("g")));
    assertEquals("VOLUME", String.valueOf(UnitsE.getValue("ml")));
  }
}