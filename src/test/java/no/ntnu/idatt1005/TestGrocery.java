package no.ntnu.idatt1005;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestGrocery {
    @Test
    void testGrocery() {
        Grocery g = new Grocery("Pasta","Bilde", 20);
        System.out.println(g.getName());
    }


}
