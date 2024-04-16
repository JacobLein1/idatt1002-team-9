package no.ntnu.idatt1005.unit;

import java.util.Arrays;

/**
 * Enum class for units
 * @version 1.0
 * @since 2024-03-29
 */
public enum UnitsE {

    ARTICLE("stk"),
    WEIGHT("g"),
    VOLUME("ml");

    private final String unit;

    UnitsE(String unit) {
        this.unit = unit;
    }

    /**
     * Method for getting the unit
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Method for getting the value of the unit
     * @param unit unit
     * @return value of the unit
     */
    public static UnitsE getValue(String unit) {
        return Arrays.stream(UnitsE.values())
                .filter(e -> e.getUnit().equalsIgnoreCase(unit))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown unit:  " + unit));
    }
}
