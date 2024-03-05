package no.ntnu.idatt1005;

import java.util.Map;
import java.util.HashMap;

public class Inventory extends Grocery {
    private final HashMap<String, Grocery> inventory;

    public Inventory(){
        inventory = new HashMap<>();
    }

    public void addGrocery(String name, int amount){
        if (inventory.containsKey(name)){
            Grocery grocery = inventory.get(name);
            grocery.setAmount(grocery.getAmount() + amount);
        } else {
            inventory.put(name, new Grocery(name, amount));
        }
    }

    public void removeEntity(String name){
        inventory.remove(name);
    }

    public void setAmount(String name, int amount){
        if (inventory.containsKey(name)){
            Grocery grocery = inventory.get(name);
            grocery.setAmount(amount);
        }
    }
}
