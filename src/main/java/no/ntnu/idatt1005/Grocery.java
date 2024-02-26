package no.ntnu.idatt1005;

public class Grocery {
    private String name;
    private String image;
    private float amount;

    Grocery(){}

    Grocery(String name, String image, float amount) {
        this.name = name;
        this.image = image;
        this.amount = amount;
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
    public float setAmount(float input) {
        return amount;
    }

}
