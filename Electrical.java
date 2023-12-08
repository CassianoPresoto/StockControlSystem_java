public class Electrical extends Product{

    private String brand;
    private Integer voltage;
    private Integer potencia;
    Electrical(String name, Float price, Integer quantity, String brand, Integer voltage, Integer potencia) {
        super(name, price, quantity);
        this.brand = brand;
        this.voltage = voltage;
        this.potencia = potencia;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getVoltage() {
        return voltage;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public String toString(){
		return super.toString() + "             " +
                "Marca: " + getBrand() + "                 " +
                "Tensão: " + getVoltage() + "                 "+
                "Potencia: " + getPotencia() + "";
		}

}
