import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PizzaDeliveryAPI implements DeliveryService {
    private List<Customer> customers;
    private List<Order> orders;
    private List<Product> menu;
    Admin admin;

    public PizzaDeliveryAPI(Admin a, ArrayList<Product> menu) {
        this.admin = a;
        customers = new ArrayList<Customer>();
        orders = new ArrayList<Order>();
        this.menu = menu;
    }

    @Override
    public User login(String u, String p) {
        if(admin.login(u, p)) return admin;

        for(Customer c : customers) {
            if(c.login(u, p)) return c;
        }

        return null;
    }

    @Override
    public Customer registerCustomer(String name, String surname, String userName, String password, String city, int zipcode, String street, int number) {
        Customer c = new Customer(name, surname, userName, password, new Address(city, street, zipcode, number));
        customers.add(c);

        return c;
    }

    @Override
    public void viewMenu() {
        int n = 1;
        // Berechnet die Anzahl der Stellen, die die längste Produktzahl benötigt (bei z.B. 50 Produkten wäre longestNumber = 2)
        int longestNumber = (int) Math.log10(menu.size()) + 1;
        System.out.println("----------------Pizza----------------\n");
        for(Product p : menu) {
            if(p.getType().toLowerCase().equals("pizza"))
                // %s = String; %[Zahl]s = Breite des Strings; %-[Zahl]s = linkszentriert & einheitlicher Abstand von Produktname und Preis
                System.out.printf("%" + longestNumber + "s. %-24s %7s$\n", n++, p.getName(), p.getPrice());
        }
        System.out.println("\n");

        System.out.println("----------------Pasta----------------\n");
        for(Product p : menu) {
            if(p.getType().toLowerCase().equals("pasta"))
                System.out.printf("%" + longestNumber + "s. %-24s %7s$\n", n++, p.getName(), p.getPrice());
        }
        System.out.println("\n");
    }

    @Override
    public void addItemToCart(Scanner sc, Customer customer) {
        System.out.println("\nEnter the number of the dish you would like to add:");
        int pick = sc.nextInt();
        customer.addToCart(menu.get(pick-1));
    }

    @Override
    public void removeItemFromCart(Scanner sc, Customer customer) {
        System.out.println("\nEnter the number of the dish you would like to remove:");
        int pick = sc.nextInt();
        customer.removeFromCart(menu.get(pick-1));
    }

    @Override
    public void viewCart(Customer customer) {
        System.out.println("\nYour shopping cart:");
        customer.showCart();
        System.out.printf("Total: %.2f$\n", customer.showTotal());
    }

    @Override
    public void placeOrder(Customer customer) {
        orders.add(customer.placeOrder());
    }

    @Override
    public void viewOrderHistory(Customer customer) {
        System.out.println();
        customer.showOrders();
    }

    @Override
    public void changeCustomerAddress(Scanner sc, Customer customer) {
        customer.changeAddress(sc);
    }

    @Override
    public void addItemToMenu(Scanner sc) {
        admin.addProduct(sc, menu);
    }

    @Override
    public void removeItemFromMenu(Scanner sc) {
        admin.removeProduct(sc, menu);
    }

    @Override
    public void viewOrders() {
        System.out.println();
        for(Order o : orders) {
            System.out.println(o + " made by Customer #" + o.getCustomer().getId() + " - " + o.getCustomer().getFullName());
        }
    }
    
}
