import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private List<Product> products;
    private double total;

    public Cart() {
        products = new ArrayList<>();
        total = 0.0;
    }

    public boolean addProduct(Product p) {
        if (p != null) {
            products.add(p); // Produkt wird dem Warenkorb hinzugefügt
            total += p.getPrice(); // Der Preis des Produktes wird zum Total addiert
            return true;
        } else return false;
    }

    public boolean removeProduct(Product p) {
        if (p != null && products.contains(p)) {
            products.remove(p); // Produkt wird aus dem Warenkorb entfernt
            total -= p.getPrice(); // Preis des Produktes wird vom Total subtrahiert
            return true;
        } else return false;
    }

    public void showProducts() {
        for(Product p : products) {
            System.out.println(p);
        }
        System.out.println();
    }

    public void clearCart() {
        if (!products.isEmpty()) {
            products.clear();
            total = 0;
        }
    }

    public double getTotal() {
        // return round(total);
        return total;
    }

    public List<Product> getProducts() {
        return products;
    }
}
