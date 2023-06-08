import java.util.HashMap;
import java.util.Map;

public class Cart {
    // Attributes
    private final Map<ProductDTO, Integer> products;
    private double total;

    // Constructor
    public Cart() {
        products = new HashMap<>();
        total = 0.0;
    }

    // Adding a product to the Cart 
    public boolean addProduct(ProductDTO p, int quant) {
        if (p != null && quant > 0) {
            int currentQuant = products.getOrDefault(p, 0);
            products.put(p, currentQuant + quant);
            total += p.getPrice() * quant; // Updating the total price
            return true;
        } else return false;
    }

    // Removing a product from the Cart
    public boolean removeProduct(ProductDTO p, int quant) {
        if (p != null && quant > 0) {
            if(products.containsKey(p)) {
                int currentQuant = products.getOrDefault(p, 0);
                if(currentQuant <= quant) {
                    products.remove(p);
                    total -= p.getPrice() * currentQuant; // Updating the total price again, if I want to remove more than currently in the cart, it removes everything and subtracts that amount time the price
                } else {
                    products.put(p, currentQuant - quant);
                    total -= p.getPrice() * quant; // Updating the total price again, subtracting the price of the removed quantity products
                }
                return true;
            }
        }
        return false;
    }

    // Listing all products in the Cart
    public void showProducts() {
        for(Map.Entry<ProductDTO, Integer> entry : products.entrySet()) {
            ProductDTO p = entry.getKey();
            int quant = entry.getValue();
            System.out.println(quant + "x - " + p);
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
    
    public Map<ProductDTO, Integer> getProducts() {
        return products;
    }
}