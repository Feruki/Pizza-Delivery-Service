import java.util.ArrayList;
import java.util.List;
// import java.util.HashMap;
// import java.util.Map;

public class Cart {
    private final List<Product> products;
    // private final Map<Product,Integer> products;
    private double total;
    // private Customer customer;

    public Cart() {
        products = new ArrayList<>();
        // products = new HashMap<>();
        total = 0.0;
        // customer = c;
    }

    // public boolean addProduct(Product p, int quantity) {
    //     if (p != null && quantity > 0) {
    //         products.put(p,quantity); // Produkt wird dem Warenkorb hinzugefügt
    //         total += round(p.getPrice() * quantity); // Der Preis des Produktes wird zum Total addiert
    //         return true;
    //     } else return false;
    // }

    public boolean addProduct(Product p) {
        if (p != null) {
            products.add(p); // Produkt wird dem Warenkorb hinzugefügt
            total += p.getPrice(); // Der Preis des Produktes wird zum Total addiert
            return true;
        } else return false;
    }

    // public boolean removeProduct(Product p, int quantity) {
    //     if (p != null && products.containsKey(p)) {
    //         double value = round(p.getPrice() * quantity);

    //         if (products.get(p) > quantity) {
    //             products.put(p,(products.get(p) - quantity)); // nur die ausgewählte Anzahl des Produktes wird aus dem Warenkorb entfernt
    //             total -= value; // Preis des Produktes wird vom Total subtrahiert
    //             return true;
    //         } else if (products.get(p) == quantity) {
    //             products.remove(p); // Produkt wird aus dem Warenkorb entfernt
    //             total -= value; // Preis des Produktes wird vom Total subtrahiert
    //             return true;
    //         } else return false;
    //     } else return false;
    // }

    public boolean removeProduct(Product p) {
        if (p != null && products.contains(p)) {
            products.remove(p); // Produkt wird aus dem Warenkorb entfernt
            total -= p.getPrice(); // Preis des Produktes wird vom Total subtrahiert
            return true;
        } else return false;
    }

    // public void showProducts() {
    //     for(Product p : products.keySet()) {
    //         System.out.println(p.toString() + " Anzahl: " + products.get(p));
    //     }
    // }

    public void showProducts() {
        for(Product p : products) {
            System.out.println(p);
        }
        System.out.println();
    }

    //TODO
    // Bräuchten diese Methode nicht unbedingt, habe sie programmieren,
    // aber darf nicht in Kombi mit den Zeilen in addProduct und removeProduct genutzt werden!
    // public double calcTotal() {
    //     for(Product p : products.keySet()) {
    //         total += (p.getPrice() * products.get(p));
    //     }
    //     return round(total);
    // }

    // public double calcTotal() {
    //     for(Product p : products) {
    //         total += p.getPrice();
    //     }
    //     return total;
    // }

    public void clearCart() {
        if (!products.isEmpty()) {
            products.clear();
        }
    }

    public double getTotal() {
        // return round(total);
        return total;
    }

    // Helper Method
    // private double round(double value) {
    //     double d = Math.pow(10,2);
    //     return Math.round(value * d) / d;
    // }
}
