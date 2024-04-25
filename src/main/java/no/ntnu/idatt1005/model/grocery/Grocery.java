package no.ntnu.idatt1005.model.grocery;

import no.ntnu.idatt1005.model.unit.UnitsE;

/**
 * Class for grocery
 * @version 1.0
 */
public class Grocery {
    private final String name;
    private final String image;
    private final String id;
    private final UnitsE unit;

    /**
     * Constructor for grocery
     * @param name name of the grocery
     * @param image image of the grocery
     * @param id id of the grocery
     * @param unit unit of the grocery
     */
    public Grocery(String name, String image, String id, UnitsE unit)
        throws IllegalArgumentException{
        if (name == null || id == null || unit == null) {
            throw new IllegalArgumentException("There was an attempt of creating a " +
                "grocery-object with null-values");
        }
        this.name = name;
        this.image = image;
        this.id = id;
        this.unit = unit;
    }

    /**
     * Method for getting the name of the grocery
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method for getting the image of the grocery
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * Method for getting the id of the grocery
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Method for getting the unit of the grocery
     * @return unit
     */
    public UnitsE getUnit() {
        return unit;
    }
}
