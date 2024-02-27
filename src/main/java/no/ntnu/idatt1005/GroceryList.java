package no.ntnu.idatt1005;

import java.util.ArrayList;

public class GroceryList {
    private ArrayList<Grocery> groceryList;

    public GroceryList(){
        this.groceryList = new ArrayList<>();
    }

    public void addGrocery(Grocery grocery){
        groceryList.add(grocery);
    }

    public void removeGrocery(Grocery grocery){
        groceryList.remove(grocery);
    }

}




