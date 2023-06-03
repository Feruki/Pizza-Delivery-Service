import java.util.Scanner;

public interface DeliveryService {
    public UserDTO login(String u, String p);
    public Customer registerCustomer(Scanner sc);
    
    public void viewMenu();
    public void addItemToCart(Scanner sc, Customer customer);
    public void removeItemFromCart(Scanner sc, Customer customer);
    public void viewCart(Customer customer);
    public void placeOrder(Customer customer);
    public void viewOrderHistory(Customer customer);
    public void changeCustomerAddress(Scanner sc, Customer customer);

    public void addItemToMenu(Scanner sc);
    public void removeItemFromMenu(Scanner sc);
    public void viewOrders();
    public void viewCustomers();

    public void save();
    public void load();
}