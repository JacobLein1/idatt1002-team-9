package no.ntnu.idatt1005.controller;

import java.util.ArrayList;
import java.util.List;
import no.ntnu.idatt1005.Recipe.Recipe;

public class BasketController {
  //opprette en liste som heter Basket som kan inneholde recipes? Og legge til en måte å kunne legge til en og en
  //recipe i denne lista, samt fjerne.

  //trenger også en metode for å generere en handleliste her, se på hvordan dette kan løses

  List<Recipe> basketOfRecipes;

  public BasketController() {
    basketOfRecipes = new ArrayList<>();
  }

  public void addRecipeToBasket(Recipe recipe) {
    basketOfRecipes.add(recipe);
  }

  public void removeRecipeFromBasket(Recipe recipe) {


  }

  public List<String[]> getShoppingListFromBasket() {
    //returnere en liste med String som sier hvilke varer man må kjøpe og hvor mye
    return null;
  }
}
