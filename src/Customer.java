import java.util.List;
import java.util.Scanner;

public class Customer extends User{
    //attributes
    private String name, surname, username, password;
    private Address address;
    private Cart shoppingcart;
    final int id;
    private static int idCounter = 0;
    List <Order> orderHistory;

    //constructor
    Customer() {
        this.name = null;
        this.surname = null;
        this.username = null;
        this.password = null;
        this.address = null; 
        this.id = -1;
    }
    Customer(String n, String s, String u, String p, Address a) {
        this.name = n;
        this.surname = s;
        this.username = u;
        this.password = p;
        this.address = a;
        this.shoppingcart = new Cart();
        this.id = idCounter;
        idCounter++;
    }

    //customer methods
    // Boolean register() {
    //     //register new customer
    //     try {
    //         Scanner sc = new Scanner(System.in);
    //         System.out.println("Enter username: ");
    //         String userName = sc.nextLine();
    //         System.out.println("Enter password: ");
    //         String password = sc.nextLine();
    //         return true;
    //     } catch (Exception e) {
    //         System.out.println("Error: " + e);
    //     }
    //     return false;  
    // }

    Boolean addToCart(Product p){
        //add product to cart
        try {
            shoppingcart.addProduct(p);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
    Boolean removeFromCart(Product p){
        //remove product from cart
        try {
            shoppingcart.removeProduct(p);
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
    void showCart() {
        //show cart
        try {
            shoppingcart.showProducts();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    double showTotal() {
        //show total value of the shoppingcart
        try {
            return shoppingcart.getTotal();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    }
    Boolean changeAddress(){
        //change adress
        try {
            //ask for new adress
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter city: ");
            address.city = sc.nextLine();
            System.out.println("Enter street: ");
            address.street = sc.nextLine();
            System.out.println("Enter zipcode: ");
            address.zipcode = sc.nextInt();
            System.out.println("Enter number: ");
            address.number = sc.nextInt();
            sc.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }

    //login from super class
    @Override
    Boolean login(String u, String p) {
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
    public Address getAddress() {
        return address;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
}
