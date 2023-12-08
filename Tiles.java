public class Tiles extends Product {

    private String dimensions;
    private String type;

    Tiles(String name, Float price, Integer quantity, String dimensions, String type) {
        super(name, price, quantity);
        this.dimensions = dimensions;
        this.type = type;
    }

    public String getDimensions() {
        return dimensions;
    }

    public String getType() {
        return type;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString(){
		return  "Name: " + getName() + "     " +
                "Price: " + getPrice() + "     " +
                "Quantity: " + getQuantity() + "     " +
                "Dimensions: " + getDimensions() + "     " +
                "Type: " + getType() ;
		}

	}
