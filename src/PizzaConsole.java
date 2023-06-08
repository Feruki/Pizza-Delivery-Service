import java.util.Scanner;

public class PizzaConsole {
    // Attributes
    private static PizzaConsole uniqueGUI = null;
    private DeliveryService api;
    private int choice;

    // Constructor
    private PizzaConsole(DeliveryService api) {
        this.api = api;
    }

    public static PizzaConsole guiInstance(DeliveryService api) {
        if(uniqueGUI == null) uniqueGUI = new PizzaConsole(api);
        return uniqueGUI;
    }

    // Starting screen
    public void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to PizzaDelivery!");

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Log in");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong input! Please try again.\n");
                run();
            }
            
            // To eat up the entered line break
            sc.nextLine();

            switch (choice) {
                case 1:
                    login(sc);
                    break;
                case 2:
                    register(sc);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // Login screen
    private void login(Scanner sc) {
        System.out.println("\nPlease enter your user name:");
        String username = sc.nextLine();

        System.out.println("Please enter your password:");
        String password = sc.nextLine();

        // Using the login method from the API which differentiates between admin and customer and returns a user object
        UserDTO user = api.login(username, password);

        // If the username and password combination is wrong, api.login returns null
        if (user == null) {
            System.out.println("Invalid username or password.");
            return;
        }
        
        // Checking if it's a customer or an admin to print the respective menu
        if (user instanceof CustomerDTO) {
            CustomerDTO customer = (CustomerDTO) user;
            System.out.println("\nWelcome " + customer.getName() + "!");
            showCustomerMenu(sc, customer);
        } else if (user instanceof AdminDTO) {
            System.out.println("\nWelcome Admin!");
            showAdminMenu(sc);
        }
    }

    // Register screen
    private void register(Scanner sc) {
        CustomerDTO customer = api.registerCustomer(sc);

        if(customer != null) {
            System.out.println("Registration successful!");
            System.out.println("Your customer ID is: " + customer.getId());
        } else {
            System.out.println("Registration failed.");
        }
    }

    // Customer menu
    private void showCustomerMenu(Scanner sc, CustomerDTO customer) {
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View menu");
            System.out.println("2. Add item to cart");
            System.out.println("3. Remove item from cart");
            System.out.println("4. View cart");
            System.out.println("5. Place order");
            System.out.println("6. View order history");
            System.out.println("7. View specific order");
            System.out.println("8. Change Address");
            System.out.println("9. Log out");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong input! Please try again.\n");
                showCustomerMenu(sc, customer);
            }

            // To eat up the entered line break
            sc.nextLine();

            switch (choice) {
                case 1:
                    api.viewMenu();
                    break;
                case 2:
                    api.addItemToCart(sc, customer);
                    break;
                case 3:
                    api.removeItemFromCart(sc, customer);
                    break;
                case 4:
                    api.viewCart(customer);
                    break;
                case 5:
                    api.placeOrder(customer);
                    break;
                case 6:
                    api.viewOrderHistory(customer);
                    break;
                case 7:
                    api.viewSpecificOrder(sc, customer);
                    break;
                case 8:
                    api.changeCustomerAddress(sc, customer);
                    break;
                case 9:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showAdminMenu(Scanner sc) {
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View menu");
            System.out.println("2. Add item to menu");
            System.out.println("3. Remove item from menu");
            System.out.println("4. Update product price");
            System.out.println("5. View orders");
            System.out.println("6. View specific order");
            System.out.println("7. View customers");
            System.out.println("8. Log out");
    
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong input! Please try again.\n");
                showAdminMenu(sc);
            }

            // To eat up the entered line break
            sc.nextLine();
    
            switch (choice) {
                case 1:
                    api.viewMenu();
                    break;
                case 2:
                    api.addItemToMenu(sc);
                    break;
                case 3:
                    api.removeItemFromMenu(sc);
                    break;
                case 4:
                    api.updateProductPrice(sc);
                    break;
                case 5:
                    api.viewOrders();
                    break;
                case 6:
                    api.viewSpecificOrder(sc);
                    break;
                case 7:
                    api.viewCustomers();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}