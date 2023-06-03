public class AdminDTO extends UserDTO {
    // No Attributes necessary
    
    // Empty Constructor, as AdminDTO just exists to distinguish between an Admin and a Customer for the different menus
    AdminDTO() {
    }
    
    // // Adding a new product to the menu
    // public boolean addProduct(Scanner sc, List<ProductDTO> menu) {
    //     System.out.println("Enter the name of the new dish you would like to add:");
    //     String name = sc.nextLine();
    //     System.out.println("How much should the new dish cost?");
    //     Double price = sc.nextDouble();
    //     sc.nextLine();
    //     System.out.println("Is it a Pizza or is it Pasta?");
    //     String type = sc.nextLine();

    //     menu.add(new ProductDTO(name, price, type));
    //     return true;
    // }
    // // Removing a product from the menu
    // public boolean removeProduct(Scanner sc, List<ProductDTO> menu) {
    //     System.out.println("\nEnter the number of the dish you would like to remove:");
    //     int pick = sc.nextInt();
    //     menu.remove(pick-1);
    //     return true;
    // }
}