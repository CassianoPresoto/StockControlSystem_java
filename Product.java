

public class Product {
    private String name;
    private Float price;
    private Integer quantity;

    public Product(String name, Float price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        System.out.println("Product created: " + this.name);
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
    
    public Float getValue() {
        return price * quantity;
    }

    public void addQuantity(Integer quantity) {
        this.quantity += quantity;
    }

    public void removeQuantity(Integer quantity) {
        this.quantity -= quantity;
    }

    public void alterPrice(Float price) {
        this.price = price;

    
    }

    public String toString() {
        return "Nome: " + getName() + "                 "+
                "Preço: R$" + getPrice() + "                 " +
                "Quantidade: " + getQuantity(); 
    }

}
