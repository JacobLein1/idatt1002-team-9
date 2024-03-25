package no.ntnu.idatt1005.Grocery;

import no.ntnu.idatt1005.Unit.Unit;
import no.ntnu.idatt1005.Unit.UnitsE;

public class Grocery {
    private final String name;
    private final String image;
    private final String id;
    private final int quantity;
    private final UnitsE unit;


    public Grocery(String name, String image, String id, UnitsE unit, int quantity) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.unit = unit;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }
    public String getImage() {
        return image;
    }

    public String getId() {
        return id;
    }

    public UnitsE getUnit() {
        return unit;
    }

    public int getQuantity() {
        return quantity;
    }


}
