package no.ntnu.idatt1005;

import java.util.List;

public class VolumeGrocery extends Grocery{
    private String unit;
    private final List<String> units = List.of("ml", "cl", "dl", "l");

    VolumeGrocery(String name, String image, float amount, String unit) {
        super(name, image, amount);
        this.unit = unit;
    }

    public String getUnit(int input) {
        return units.get(input);
    }

    public void setUnit() {
        this.unit = unit;
    }
}
