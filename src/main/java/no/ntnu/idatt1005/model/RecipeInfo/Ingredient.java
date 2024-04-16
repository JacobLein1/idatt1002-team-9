package no.ntnu.idatt1005.model.RecipeInfo;

import no.ntnu.idatt1005.model.grocery.Grocery;

/**
 * Class for ingredient, which is a grocery with an amount
 * @version 1.0
 */
public class Ingredient {
  private final Grocery grocery;
  private final double amount;

  /**
   * Constructor for ingredient
   * @param grocery grocery
   * @param amount amount of the grocery
   */
  public Ingredient(Grocery grocery, double amount) throws IllegalArgumentException{
    if (grocery == null) {
      throw new IllegalArgumentException("Cannot create a grocery-object with a null-value");
    }
    this.grocery = grocery;
    this.amount = amount;
  }

  /**
   * Method for getting the grocery
   * @return grocery
   */
  public Grocery getGrocery() {
    return grocery;
  }

  /**
   * Method for getting the amount
   * @return amount
   */
  public double getAmount() {
    return amount;
  }

  @Override
  public String toString() {
    return grocery.getName() +
            ", " + amount + grocery.getUnit().getUnit();
  }
}

