import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {
    //attributes
    private final int id;
    private static int idCounter = 1;
    private Customer c;
    private List<Product> p;
    private double totalCost;

    //constructor
    public Order(Customer customer) {
        p = new ArrayList<Product>();
        this.id = idCounter;
        idCounter++;
        this.c = customer;
        for(Product oProduct : customer.getCart().getProducts()) {
            p.add(oProduct);
        }       
        this.totalCost = customer.showTotal();
    }

    @Override
    public String toString() {
        return "Order #" + id + " - " + p;
    }

    public Customer getCustomer() {
        return c;
    }

    public double getTotalCost() {
        return totalCost;
    }
}