package no.ntnu.idatt1005.Grocery;

import no.ntnu.idatt1005.Unit.Unit;
import no.ntnu.idatt1005.Unit.UnitsE;

public class Grocery {
    private String name;
    private String image;
    private float amount;
    private final String id;
    private UnitsE unit;


    Grocery(String name, String image, float amount, String id, UnitsE unit) {
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.id = id;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }
    public String getImage() {
        return image;
    }

    public float getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public UnitsE getUnit() {
        return unit;
    }

    public float setAmount(float input) {
        return amount;
    }

}
