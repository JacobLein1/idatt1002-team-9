package no.ntnu.idatt1005.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroceryControllerTest {
    GroceryController groceryController = new GroceryController();
   @Test
    void getAllGroceries() {
       assertEquals(10, groceryController.getAllGroceries().size());
    }
    @Test
    void getGroceryById(){
        Assert.assertEquals("Milk", groceryController.getGroceryById("1").getName());
    }
}