public class ProductDTO {
    // Attributes
    private String name;
    private double price;
    private String type;

    // Constructor
    public ProductDTO(String name, double price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    // Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    // For easier printing of the menu
    @Override
    public String toString() {
        return name + ": " + price + "$";
    }
}
