public class Structures extends Product {

    private String dimensions;
    private Float weight;

    Structures(String name, Float price, Integer quantity, String dimensions, Float weight) {
        super(name, price, quantity);

        this.weight = weight;
        this.dimensions = dimensions;

    }

    public String getDimensions() {
        return dimensions;
    }

    public Float getWeight() {
        return weight;
    }

    public String toString() {
        return "Name: " + getName() + "     " +
                "Price: " + getPrice() + "     " +
                "Quantity: " + getQuantity() + "     " +
                "Dimensions: " + getDimensions() + "     " +
                "Weight: " + getWeight();
    }

}
