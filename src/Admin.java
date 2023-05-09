import java.util.List;
import java.util.Scanner;

public class Admin extends User{
    //attributes
    private String username, password;
    
    //constructor
    Admin() {
        username = "admin";
        password = "adminPw";
    }

    //login with admin credentials
    @Override
    public boolean login(String u, String p) {
        if(u.equals(username) && p.equals(password)){
            return true;
        }
        return false;
    }


    //admin methods

    //add new product
    public boolean addProduct(Scanner sc, List<Product> menu) {
        System.out.println("Enter the name of the new dish you would like to add:");
        String name = sc.nextLine();
        System.out.println("How much should the new dish cost?");
        Double price = sc.nextDouble();
        sc.nextLine();
        System.out.println("Is it a Pizza or is it Pasta?");
        String type = sc.nextLine();

        menu.add(new Product(name, price, type));
        return true;
    }
    //remove product
    public boolean removeProduct(Scanner sc, List<Product> menu) {
        System.out.println("\nEnter the number of the dish you would like to remove:");
        int pick = sc.nextInt();
        menu.remove(pick-1);
        return true;
    }
}