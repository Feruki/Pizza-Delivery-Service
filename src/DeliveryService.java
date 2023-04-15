import java.util.Scanner;

public interface DeliveryService {
    User login(String u, String p);
    Customer registerCustomer(String name, String surname, String userName, String password, String city, int zipcode, String street, int number);
    void viewMenu();
    void addItemToCart(Scanner sc, Customer customer);
    void removeItemFromCart(Scanner sc, Customer customer);
    void viewCart(Customer customer);
    void placeOrder(Customer customer);
    void viewOrderHistory(Customer customer);
    void changeCustomerAddress(Scanner sc, Customer customer);

    void addItemToMenu(Scanner sc);
    void removeItemFromMenu(Scanner sc);
    void viewOrders();
}