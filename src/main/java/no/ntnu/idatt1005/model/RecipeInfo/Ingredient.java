package no.ntnu.idatt1005.model.RecipeInfo;

public class Ingredient {
  private final String groceryId;
  private final String groceryName;
  private final double amount;

  public Ingredient(String groceryId, String groceryName, double amount) {
    this.groceryId = groceryId;
    this.groceryName = groceryName;
    this.amount = amount;
  }

  public String getGroceryId() {
    return groceryId;
  }

  public String getGroceryName() {
    return groceryName;
  }

  public double getAmount() {
    return amount;
  }
}
