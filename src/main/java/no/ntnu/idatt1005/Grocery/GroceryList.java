package no.ntnu.idatt1005.Grocery;

import java.util.HashMap;

public class GroceryList {

    private HashMap<String, Grocery> groceryList;

    public GroceryList() {
        this.groceryList = new HashMap<>();
    }

    public void addGrocery(Grocery grocery) {
        this.groceryList.put(grocery.getId(), grocery);
    }

    public void removeGrocery(String id) {
        this.groceryList.remove(id);
    }

    public HashMap<String, Grocery> getGroceryList() {
        return this.groceryList;
    }

    public Grocery getGrocery(String id) {
        return this.groceryList.get(id);
    }

    public void setGroceryUnit(String id, String unit) {
        this.groceryList.get(id).setUnit(unit);
    }

}
