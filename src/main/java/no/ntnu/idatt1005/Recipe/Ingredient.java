package no.ntnu.idatt1005.Recipe;

public class Ingredient {
  private final String groceryId;
  private final double amount;

  public Ingredient(String groceryId, double amount) {
    this.groceryId = groceryId;
    this.amount = amount;
  }

  public String getGroceryId() {
    return groceryId;
  }

  public double getAmount() {
    return amount;
  }
}
