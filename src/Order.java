import java.util.ArrayList;
import java.util.List;

public class Order {
    //attributes
    final int id;
    private static int idCounter = 1;
    private Customer c;
    List<Product> p;
    double totalCost;

    //constructor
    Order(Customer customer) {
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
}
