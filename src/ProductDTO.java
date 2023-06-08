import java.util.Objects;

public class ProductDTO {
    // Attributes
    private String name;
    private double price;
    private String type;
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int pID) {
        this.id = pID;
    }

    // For easier printing of the menu
    @Override
    public String toString() {
        return name + ": " + price + "$";
    }

    // Overriding equals and hash methods as we're pulling a new object from the database, so we have to check for attribute equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProductDTO other = (ProductDTO) obj;
        return Objects.equals(name, other.name) &&
            Objects.equals(price, other.price) &&
            Objects.equals(type, other.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, type);
    }
}
