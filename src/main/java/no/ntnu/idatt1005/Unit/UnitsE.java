package no.ntnu.idatt1005.Unit;

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

}
