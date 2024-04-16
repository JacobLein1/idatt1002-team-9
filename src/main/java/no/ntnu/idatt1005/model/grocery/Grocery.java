package no.ntnu.idatt1005.model.grocery;

import no.ntnu.idatt1005.unit.UnitsE;

public class Grocery {
    private final String name;
    private final String image;
    private final String id;
    private final UnitsE unit;


    public Grocery(String name, String image, String id, UnitsE unit) {
        this.name = name;
        this.image = image;
        this.id = id;
        this.unit = unit;
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


}
