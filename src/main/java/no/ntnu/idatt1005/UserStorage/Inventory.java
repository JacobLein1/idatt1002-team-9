package no.ntnu.idatt1005.UserStorage;

import no.ntnu.idatt1005.Grocery.Grocery;

import java.util.HashMap;

public class Inventory {

 private HashMap<Grocery, Integer> inventory;
 private int groceryAmount;
 private int stockSize;

 public Inventory() {
  this.stockSize = 0;
  this.inventory = new HashMap<>();
  this.groceryAmount = 0;
 }

 public void addGrocery(Grocery grocery, int amount) {
   inventory.put(grocery, amount);
   stockSize++;
 }

    public void removeGrocery(Grocery grocery, int amount) {
        inventory.remove(grocery);
        stockSize--;
    }

    public void updateGroceryAmount(Grocery grocery, int amount) {
     if (this.inventory.containsKey(grocery)) {
         int amountToAdd = inventory.get(grocery) + amount;
         inventory.put(grocery, amountToAdd);
     } else {
         addGrocery(grocery, amount);
         System.out.println("Did not exist, added to list"); // should ask if user wants to add to list
     }
    }

}
