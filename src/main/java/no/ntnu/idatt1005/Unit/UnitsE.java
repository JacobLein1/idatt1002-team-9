package no.ntnu.idatt1005.Unit;

import java.util.Arrays;

public enum UnitsE {

    ARTICLE("stk"),
    WEIGHT("g"),
    VOLUME("ml");

    private final String unit;

    UnitsE(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public static UnitsE getValue(String unit) {
        return Arrays.stream(UnitsE.values())
                .filter(e -> e.getUnit().equalsIgnoreCase(unit))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown unit:  " + unit));
    }

}
