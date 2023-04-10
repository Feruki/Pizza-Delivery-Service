import java.util.Scanner;

public class PizzaConsole {
    private DeliveryService api;

    public PizzaConsole(DeliveryService api) {
        this.api = api;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to PizzaDelivery!");

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Log in");
            System.out.println("2. Register");
            System.out.println("3. Exit");

            int choice = sc.nextInt();
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

    private void login(Scanner sc) {
        System.out.println("\nPlease enter your user name:");
        String username = sc.nextLine();

        System.out.println("Please enter your password:");
        String password = sc.nextLine();

        User user = api.login(username, password);

        if (user == null) {
            System.out.println("Invalid username or password.");
            return;
        }
        
        // if (userName == null) {
        //     System.out.println("Invalid email or password.");
        //     return;
        // }
        
        if (user instanceof Customer) {
            Customer customer = (Customer) user;
            System.out.println("\nWelcome " + customer.getName() + "!");
            showCustomerMenu(sc, customer);
        } else if (user instanceof Admin) {
            System.out.println("\nWelcome Admin!");
            showAdminMenu(sc);
        }

        // if (userName.equals("Customer")) {
        //     System.out.println("\nWelcome Customer!");
        //     showCustomerMenu(sc);
        // } else if (userName.equals("Admin")) {
        //     System.out.println("\nWelcome Admin!");
        //     showAdminMenu(sc);
        // }
    }

    private void register(Scanner sc) {
        System.out.println("Please enter your user name:");
        String userName = sc.nextLine();
        System.out.println("Please enter your password:");
        String password = sc.nextLine();
        
        System.out.println("\nPlease enter your name:");
        String name = sc.nextLine();
        System.out.println("Please enter your surname:");
        String surname = sc.nextLine();


        System.out.println("\nPlease enter your address:");
        System.out.println("City:");
        String city = sc.nextLine();
        System.out.println("Zipcode:");
        int zipcode = sc.nextInt();
        System.out.println("Street:");
        String street = sc.nextLine();
        System.out.println("Streetnumber:");
        int number = sc.nextInt();
        
        Customer customer = api.registerCustomer(name, surname, userName, password, city, zipcode, street, number);

        if(customer != null) {
        // if (userName != null) {
            System.out.println("Registration successful!");
            System.out.println("Your customer ID is: " + customer.getId());
        } else {
            System.out.println("Registration failed.");
        }
    }

    private void showCustomerMenu(Scanner sc, Customer customer) {
    // private void showCustomerMenu(Scanner sc) {
        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. View menu");
            System.out.println("2. Add item to cart");
            System.out.println("3. Remove item from cart");
            System.out.println("4. View cart");
            System.out.println("5. Place order");
            System.out.println("6. View order history");
            System.out.println("7. Log out");

            int choice = sc.nextInt();

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
            System.out.println("4. View orders");
            System.out.println("5. Log out");
    
            int choice = sc.nextInt();
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
                    api.viewOrders();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}