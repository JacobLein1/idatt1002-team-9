package no.ntnu.idatt1005.model.RecipeInfo;

import no.ntnu.idatt1005.model.grocery.Grocery;

public class Ingredient {
  private Grocery grocery;
  private final double amount;

  public Ingredient(Grocery grocery, double amount) {
    this.grocery = grocery;
    this.amount = amount;
  }

  public Grocery getGrocery() {
    return grocery;
  }

  public double getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return grocery.getName() +
            ", " + amount + grocery.getUnit().getUnit();
  }
}

