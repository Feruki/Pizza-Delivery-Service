import java.util.Scanner;

public interface DeliveryService {
    public UserDTO login(String u, String p);
    public CustomerDTO registerCustomer(Scanner sc);
    
    public void viewMenu();
    public void addItemToCart(Scanner sc, CustomerDTO customer);
    public void removeItemFromCart(Scanner sc, CustomerDTO customer);
    public void viewCart(CustomerDTO customer);
    public void placeOrder(CustomerDTO customer);
    public void viewOrderHistory(CustomerDTO customer);
    public void viewSpecificOrder(Scanner sc, CustomerDTO customer);
    public void changeCustomerAddress(Scanner sc, CustomerDTO customer);

    public void addItemToMenu(Scanner sc);
    public void removeItemFromMenu(Scanner sc);
    public void updateProductPrice(Scanner sc);
    public void viewOrders();
    public void viewSpecificOrder(Scanner sc);
    public void viewCustomers();
}