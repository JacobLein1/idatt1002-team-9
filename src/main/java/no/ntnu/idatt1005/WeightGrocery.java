package no.ntnu.idatt1005;

import java.util.List;

public class WeightGrocery extends Grocery{
    private String unit;
    private List<String> units = List.of("g","hg","kg");

    WeightGrocery(String name, String image, float amount,String unit) {
        super(name, image, amount);
        this.unit = unit;
    }

    public String getUnit(int input) {
        return units.get(input);
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
}
