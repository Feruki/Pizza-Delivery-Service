import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryServiceImpl implements DeliveryService, Serializable {
    private List<Customer> customers;
    private List<Order> orders;
    private List<ProductDTO> menu;
    private AdminDTO admin;

    public DeliveryServiceImpl(AdminDTO a) {
        this.admin = a;
        customers = new ArrayList<Customer>();
        orders = new ArrayList<Order>();
        load();
    }

    @Override
    public UserDTO login(String u, String p) {
        if(admin.login(u, p)) return admin;

        for(Customer c : customers) {
            if(c.login(u, p)) return c;
        }

        return null;
    }

    @Override
    public Customer registerCustomer(Scanner sc) {
        System.out.println("\nPlease enter your user name:");
        String username = sc.nextLine();
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
        int zipcode = 0;
        try {
            zipcode = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Wrong Input! Please try again.");
            registerCustomer(sc);
        }
        sc.nextLine();
        System.out.println("Street:");
        String street = sc.nextLine();
        System.out.println("Streetnumber:");
        int number = 0;
        try {
            number = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Wrong Input! Please try again.");
            registerCustomer(sc);
        }
        // To eat up the entered line break
        sc.nextLine();

        Customer c = new Customer(name, surname, username, password, new AddressDTO(city, street, zipcode, number));
        customers.add(c); // Adding the customer to the total customer list

        return c;
    }

    @Override
    public void viewMenu() {
        int n = 1;
        // Calculating the length of the longest product number (For example with 50 products longestNumber would be 2)
        int longestNumber = (int) Math.log10(menu.size()) + 1;
        System.out.println("----------------Pizza----------------\n");
        for(ProductDTO p : menu) {
            if(p.getType().toLowerCase().equals("pizza"))
                // %s = String; %[num]s = Width of the string; %-[num]s = left centered & same spacing between product name and price
                System.out.printf("%" + longestNumber + "s. %-24s %7s$\n", n++, p.getName(), p.getPrice());
        }
        System.out.println("\n");

        System.out.println("----------------Pasta----------------\n");
        for(ProductDTO p : menu) {
            if(p.getType().toLowerCase().equals("pasta"))
                // %s = string; %[num]s = Width of the string; %-[num]s = left centered & same spacing between product name and price
                System.out.printf("%" + longestNumber + "s. %-24s %7s$\n", n++, p.getName(), p.getPrice());
        }
        System.out.println("\n");
    }

    @Override
    public void addItemToCart(Scanner sc, Customer customer) {
        int pick = 0;
        System.out.println("\nEnter the number of the dish you would like to add:");
        try {
            pick = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Wrong Input! Please try again.");
            registerCustomer(sc);
        }
        customer.addToCart(menu.get(pick-1)); // Because of indeces we have to subtract 1, if the customer wants the 10th item it'll be the index 9
    }

    @Override
    public void removeItemFromCart(Scanner sc, Customer customer) {
        int pick = 0;
        System.out.println("\nEnter the number of the dish you would like to remove:");
        try {
            pick = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Wrong Input! Please try again.");
            registerCustomer(sc);
        }
        customer.removeFromCart(menu.get(pick-1)); // Because of indeces we have to subtract 1, if the customer wants the 10th item it'll be the index 9
    }

    @Override
    public void viewCart(Customer customer) {
        System.out.println("\nYour shopping cart:");
        customer.showCart();
        System.out.printf("Total: %.2f$\n", customer.showTotal());
    }

    @Override
    public void placeOrder(Customer customer) {
        orders.add(customer.placeOrder());
    }

    @Override
    public void viewOrderHistory(Customer customer) {
        System.out.println();
        customer.showOrders();
    }

    @Override
    public void changeCustomerAddress(Scanner sc, Customer customer) {
        customer.changeAddress(sc);
    }

    @Override
    public void addItemToMenu(Scanner sc) {
        admin.addProduct(sc, menu);
    }

    @Override
    public void removeItemFromMenu(Scanner sc) {
        admin.removeProduct(sc, menu);
    }

    @Override
    public void viewOrders() {
        System.out.println();
        for(Order o : orders) {
            System.out.println(o + " made by Customer #" + o.getCustomer().getId() + " - " + o.getCustomer().getFullName());
        }
    }

    @Override
    public void viewCustomers() {
        System.out.println();
        for(Customer c : customers) {
            System.out.println(c);
        } 
    }

    @Override
    public void save() {
        // Saving customers
        try (FileOutputStream fileOut = new FileOutputStream("./ser/customer.ser"); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(customers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Saving orders
        try (FileOutputStream fileOut = new FileOutputStream("./ser/orders.ser"); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Saving the menu
        try (FileOutputStream fileOut = new FileOutputStream("./ser/menu.ser"); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    @Override
    @SuppressWarnings("unchecked") // Just makes it so the IDE doesn't show the warnings anymore
    public void load() {
        // Loading customers
        try (FileInputStream fileIn = new FileInputStream("./ser/customer.ser"); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            customers = (ArrayList<Customer>) objectIn.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Loading orders
        try (FileInputStream fileIn = new FileInputStream("./ser/orders.ser"); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            orders = (ArrayList<Order>) objectIn.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Loading the menu
        try (FileInputStream fileIn = new FileInputStream("./ser/menu.ser"); ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            menu = (ArrayList<ProductDTO>) objectIn.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}