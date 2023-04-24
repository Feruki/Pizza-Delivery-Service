import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User{
    //attributes
    private String name, surname, username, password;
    private Address address;
    private Cart shoppingcart;
    private final int id;
    private static int idCounter = 0;
    private List <Order> orderHistory;

    //constructor
    public Customer() {
        this.name = null;
        this.surname = null;
        this.username = null;
        this.password = null;
        this.address = null; 
        this.id = -1;
    }

    public Customer(String n, String s, String u, String p, Address a) {
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

    public boolean addToCart(Product p){
        //add product to cart
        try {
            shoppingcart.addProduct(p);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
    public boolean removeFromCart(Product p){
        //remove product from cart
        try {
            shoppingcart.removeProduct(p);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
    public void showCart() {
        //show cart
        try {
            shoppingcart.showProducts();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    public double showTotal() {
        //show total value of the shoppingcart
        try {
            return shoppingcart.getTotal();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }
    public boolean changeAddress(Scanner sc){
        //change adress
        try {
            //ask for new adress
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

    public Order placeOrder() {
        Order o = new Order(this);
        orderHistory.add(o);
        shoppingcart.clearCart();
        return o;
    }

    public void showOrders() {
        for(Order o : orderHistory) {
            System.out.println(o);
        }
    }

    //login from super class
    @Override
    public boolean login(String u, String p) {
        if(u.equals(username) && p.equals(password)){
            return true;
        }
        return true;
    }
   
    //getter and setter
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
    
    public Address getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public Cart getCart() {
        return shoppingcart;
    }
}
