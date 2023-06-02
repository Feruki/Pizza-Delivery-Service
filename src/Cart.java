import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    // Attributes
    private final List<ProductDTO> products;
    private double total;

    // Constructor
    public Cart() {
        products = new ArrayList<>();
        total = 0.0;
    }

    // Adding a product to the Cart 
    public boolean addProduct(ProductDTO p) {
        if (p != null) {
            products.add(p);
            total += p.getPrice(); // Updating the total price
            return true;
        } else return false;
    }

    // Removing a product from the Cart
    public boolean removeProduct(ProductDTO p) {
        if (p != null && products.contains(p)) {
            products.remove(p); 
            total -= p.getPrice(); // Updating the total price again, subtracting the price of the removed product
            return true;
        } else return false;
    }

    // Listing all products in the Cart
    public void showProducts() {
        for(ProductDTO p : products) {
            System.out.println(p);
        }
        System.out.println();
    }

    // Clearing the cart (After an Order was placed for example)
    public void clearCart() {
        if (!products.isEmpty()) {
            products.clear();
            total = 0; // Resetting the total price to 0
        }
    }

    // Getters
    public double getTotal() {
        return total;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }
}