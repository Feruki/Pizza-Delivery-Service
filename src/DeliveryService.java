import java.util.Scanner;

public interface DeliveryService {
    public User login(String u, String p);
<<<<<<< HEAD
    public Customer registerCustomer(Scanner sc);
=======
    public Customer registerCustomer(String name, String surname, String userName, String password, String city, int zipcode, String street, int number);
>>>>>>> 2ff700281becb7372e48c7739887dec7cf2089de
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
<<<<<<< HEAD
    public void viewCustomers();

    public void save();
    public void load();
=======
>>>>>>> 2ff700281becb7372e48c7739887dec7cf2089de
}