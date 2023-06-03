import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDTO extends UserDTO implements Serializable {
    // Attributes
    private String name, surname, username, password;
    private AddressDTO address;
    private Cart shoppingcart;
    private final int id;
    private static int idCounter = 0; // As we can not serialize static attributes, this will reset upon loading and is a flaw of Serializable
    private List <Order> orderHistory;

    // Constructor
    public CustomerDTO(String n, String s, String u, String p, AddressDTO a) {
        orderHistory = new ArrayList<Order>();
        this.name = n;
        this.surname = s;
        this.username = u;
        this.password = p;
        this.address = a;
        this.shoppingcart = new Cart();
        this.id = idCounter;
        idCounter++;
    }
    
    // Adding a product to the Cart
    public boolean addToCart(ProductDTO p){
        try {
            shoppingcart.addProduct(p);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    // Removing a product from the Cart
    public boolean removeFromCart(ProductDTO p){
        try {
            shoppingcart.removeProduct(p);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    // Showing all products in the Cart
    public void showCart() {
        try {
            shoppingcart.showProducts();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Showing the total cost of the Cart
    public double showTotal() {
        try {
            return shoppingcart.getTotal();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }

    // Changign the Address of the Customer
    public boolean changeAddress(Scanner sc){
        try {
            System.out.println("Enter city: ");
            address.setCity(sc.nextLine());
            System.out.println("Enter street: ");
            address.setStreet(sc.nextLine());
            System.out.println("Enter zipcode: ");
            address.setZipcode(sc.nextInt());
            System.out.println("Enter number: ");
            address.setNumber(sc.nextInt());
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    // Placing the order
    // public Order placeOrder() {
    //     Order o = new Order(this); 
    //     orderHistory.add(o);
    //     shoppingcart.clearCart(); // After the order was placed, the Cart needs to be cleared again
    //     return o; // Returning the order to add it to the list of total Orders
    // }

    // Showing the order history of a specific customer
    public void showOrders() {
        for(Order o : orderHistory) {
            System.out.println(o);
        }
    }

    // Login from with customer credentials
    @Override
    public boolean login(String u, String p) {
        if(u.equals(username) && p.equals(password)){
            return true;
        }
        return true;
    }
   
    // Getter and Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFullName() {
        return surname + ", " + name;
    }
    
    public AddressDTO getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public Cart getCart() {
        return shoppingcart;
    }

    // For easier printing of the customer list
    @Override
    public String toString() {
        return "Customer #" + id + " - " + surname + ", " + name;
    }
}