package no.ntnu.idatt1005.model.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import no.ntnu.idatt1005.model.RecipeInfo.Ingredient;

public class Inventory {
  private final List<Ingredient> inventory;

  public Inventory() {
    inventory = new ArrayList<>();
  }

  public List<Ingredient> getInventory() {
    return inventory;
  }

  public void addGroceryToInventory(Ingredient invEntity) {
    inventory.add(invEntity);
  }
}
