import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    // Attributes
    private String username, password;
    
    // Constructor
    Admin() {
        username = "admin";
        password = "admin";
    }

    // Login with admin credentials (Hard coded as of now)
    @Override
    public boolean login(String u, String p) {
        if(u.equals(username) && p.equals(password)){
            return true;
        }
        return false;
    }
    
    // Adding a new product to the menu
    public boolean addProduct(Scanner sc, List<ProductDTO> menu) {
        System.out.println("Enter the name of the new dish you would like to add:");
        String name = sc.nextLine();
        System.out.println("How much should the new dish cost?");
        Double price = sc.nextDouble();
        sc.nextLine();
        System.out.println("Is it a Pizza or is it Pasta?");
        String type = sc.nextLine();

        menu.add(new ProductDTO(name, price, type));
        return true;
    }
    // Removing a product from the menu
    public boolean removeProduct(Scanner sc, List<ProductDTO> menu) {
        System.out.println("\nEnter the number of the dish you would like to remove:");
        int pick = sc.nextInt();
        menu.remove(pick-1);
        return true;
    }
}