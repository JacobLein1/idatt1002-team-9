package no.ntnu.idatt1005.Grocery;

import no.ntnu.idatt1005.Unit.Unit;

public class Grocery {
    private String name;
    private String image;
    private float amount;
    private final String id;
    private Unit unit;


    Grocery(String name, String image, float amount, String id, Unit unit) {
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

    public Unit getUnit() {
        return unit;
    }

    public float setAmount(float input) {
        return amount;
    }

}
