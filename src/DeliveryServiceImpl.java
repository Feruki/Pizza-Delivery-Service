import java.util.List;
import java.util.Scanner;

public class DeliveryServiceImpl implements DeliveryService {
    // Attributes
    private AddressDAOImpl addrDAO;
    private AdminDAOImpl admDAO;
    private CustomerDAOImpl cusDAO;
    private ProductDAOImpl prdDAO;

    // Constructor
    public DeliveryServiceImpl(AddressDAOImpl aDAO, AdminDAOImpl adminDAO, CustomerDAOImpl cDAO, ProductDAOImpl pDAO) {
        this.addrDAO = aDAO;
        this.admDAO = adminDAO;
        this.cusDAO = cDAO;
        this.prdDAO = pDAO;
    }

    // Login method
    @Override
    public UserDTO login(String u, String p) {
        if(admDAO.login(u, p)) return new AdminDTO();
        else if(cusDAO.login(u, p)) return cusDAO.getCustomerByUsername(u);
        return null;
    }

    // Register method
    @Override
    public CustomerDTO registerCustomer(Scanner sc) {
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
        // To eat up the entered line break
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

        // Transforming the street to a standardized String in order to reduce duplicates
        street = street.toLowerCase();
        street = street.replace("strasse", "str.");
        street = street.replace("str.", "Str.");
        street = street.substring(0, 1).toUpperCase() + street.substring(1);

        // Transforming the city to a standardized String in order tor reduced duplicates
        city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();


        CustomerDTO c = new CustomerDTO(name, surname, username, password, new AddressDTO(city, street, zipcode, number));
        c.setId(cusDAO.saveCustomer(c, addrDAO));

        if(c.getId() == -1) return null;
        return c;
    }

    // Printing the menu
    @Override
    public void viewMenu() {
        List<ProductDTO> menu = prdDAO.getMenu();
        // Calculating the length of the longest product number (For example with 50 products longestNumber would be 2)
        int longestNumber = (int) Math.log10(menu.size()) + 1;
        System.out.println("----------------Pizza----------------\n");
        for(ProductDTO p : menu) {
            if(p.getType().toLowerCase().equals("pizza"))
                // %s = String; %[num]s = Width of the string; %-[num]s = left centered & same spacing between product name and price
                System.out.printf("%" + longestNumber + "s. %-24s %7s$\n", p.getId(), p.getName(), p.getPrice());
        }
        System.out.println("\n");

        System.out.println("----------------Pasta----------------\n");
        for(ProductDTO p : menu) {
            if(p.getType().toLowerCase().equals("pasta"))
                // %s = string; %[num]s = Width of the string; %-[num]s = left centered & same spacing between product name and price
                System.out.printf("%" + longestNumber + "s. %-24s %7s$\n", p.getId(), p.getName(), p.getPrice());
        }
        System.out.println("\n");

        System.out.println("----------------Salad----------------\n");
        for(ProductDTO p : menu) {
            if(p.getType().toLowerCase().equals("salad"))
                // %s = string; %[num]s = Width of the string; %-[num]s = left centered & same spacing between product name and price
                System.out.printf("%" + longestNumber + "s. %-24s %7s$\n", p.getId(), p.getName(), p.getPrice());
        }
        System.out.println("\n");

        System.out.println("---------------Dessert---------------\n");
        for(ProductDTO p : menu) {
            if(p.getType().toLowerCase().equals("dessert"))
                // %s = string; %[num]s = Width of the string; %-[num]s = left centered & same spacing between product name and price
                System.out.printf("%" + longestNumber + "s. %-24s %7s$\n", p.getId(), p.getName(), p.getPrice());
        }
        System.out.println("\n");

        System.out.println("----------------Drinks---------------\n");
        for(ProductDTO p : menu) {
            if(p.getType().toLowerCase().equals("drinks"))
                // %s = string; %[num]s = Width of the string; %-[num]s = left centered & same spacing between product name and price
                System.out.printf("%" + longestNumber + "s. %-24s %7s$\n", p.getId(), p.getName(), p.getPrice());
        }
        System.out.println("\n");
    }

    @Override
    public void addItemToCart(Scanner sc, CustomerDTO customer) {
        int pick = 0;
        int quant = 1;
        System.out.println("\nEnter the number of the dish you would like to add:");
        try {
            pick = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Wrong Input! Please try again.");
            registerCustomer(sc);
        }
        // To eat up the entered line break
        sc.nextLine();
        System.out.println("Please enter the desired quantity:");
        try {
            quant = sc.nextInt();
        } catch (Exception e) {
            quant = 1;
        }
        // To eat up the entered line break
        sc.nextLine();
        cusDAO.addToCart(prdDAO.getProductById(pick), quant, customer);
    }

    @Override
    public void removeItemFromCart(Scanner sc, CustomerDTO customer) {
        int pick = 0;
        int quant = 1;
        System.out.println("\nEnter the number of the dish you would like to remove:");
        try {
            pick = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Wrong Input! Please try again.");
            registerCustomer(sc);
        }
        System.out.println("Please enter the desired quantity:");
        try {
            quant = sc.nextInt();
        } catch (Exception e) {
            quant = 1;
        }
        cusDAO.removeFromCart(prdDAO.getProductById(pick), quant, customer);
    }

    @Override
    public void viewCart(CustomerDTO customer) {
        System.out.println("\nYour shopping cart:");
        customer.showCart();
        System.out.printf("Total: %.2f$\n", customer.showTotal());
    }

    @Override
    public void placeOrder(CustomerDTO customer) {
        cusDAO.placeOrder(customer);
    }

    @Override
    public void viewOrderHistory(CustomerDTO customer) {
        System.out.println();
        cusDAO.showOrders(customer);
    }

    @Override
    public void viewSpecificOrder(Scanner sc, CustomerDTO customer) {
        int pick = 0;
        System.out.println("\nEnter the number of the order you would like to see:");
        try {
            pick = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Wrong Input! Please try again.");
            viewSpecificOrder(sc, customer);
        }
        cusDAO.showSpecificOrder(customer, pick);
    }

    @Override
    public void changeCustomerAddress(Scanner sc, CustomerDTO customer) {
        System.out.println("\nPlease enter the new address:");
        System.out.println("City:");
            String city = sc.nextLine();
        System.out.println("Zipcode:");
            int zipcode = 0;
        try {
            zipcode = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Wrong Input! Please try again.");
            changeCustomerAddress(sc, customer);
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
            changeCustomerAddress(sc, customer);
        }
        addrDAO.updateAddress(customer, new AddressDTO(city, street, zipcode, number));
    }

    @Override
    public void addItemToMenu(Scanner sc) {
        System.out.println("Enter the name of the new dish you would like to add:");
            String name = sc.nextLine();
        System.out.println("How much should the new dish cost?");
            Double price = 0.0;
        try {
            price = sc.nextDouble();
        } catch(Exception e) {
            System.out.println("Wrong Input! Please try again.");
            addItemToMenu(sc);
        }
        // To eat up the entered line break
        sc.nextLine();
        System.out.println("Enter the Product Type (Pizza, Pasta, Salad, Dessert, Drinks):");
        String type = sc.nextLine().toLowerCase();
        if(!type.equalsIgnoreCase("Pizza") && !type.equalsIgnoreCase("Pasta") && !type.equalsIgnoreCase("Salad") && !type.equalsIgnoreCase("Dessert") && !type.equalsIgnoreCase("Drinks")) {
            System.out.println("Wrong Input! Please try again.");
            addItemToMenu(sc);
        }

        type = type.substring(0, 1).toUpperCase() + type.substring(1);

        admDAO.addProduct(new ProductDTO(name, price, type));
    }

    @Override
    public void removeItemFromMenu(Scanner sc) {
        System.out.println("\nEnter the number of the dish you would like to remove:");
        int pick = 0;
        try {
            pick = sc.nextInt();
        } catch(Exception e) {
            System.out.println("Wrong Input! Please try again.");
            removeItemFromMenu(sc);
        }
        // To eat up the entered line break
        sc.nextLine();

        admDAO.removeProduct(pick);
    }

    @Override
    public void updateProductPrice(Scanner sc) {
        int pick = 0;
        double price = 0.0;
        System.out.println("\nEnter the number of the dish you would like to update the price of:");
        try {
            pick = sc.nextInt();
        } catch(Exception e) {
            System.out.println("Wrong Input! Please try again.");
            updateProductPrice(sc);
        }
        System.out.println("Enter the new price of the product:");
        try {
            price = sc.nextDouble();
        } catch(Exception e) {
            System.out.println("Wrong Input! Please try again.");
            updateProductPrice(sc);
        }

        admDAO.updatePrice(pick, price);
    }

    @Override
    public void viewOrders() {
        System.out.println();
        admDAO.viewOrders();
    }

    @Override
    public void viewSpecificOrder(Scanner sc) {
        int pick = 0;
        System.out.println("\nEnter the number of the order you would like to see:");
        try {
            pick = sc.nextInt();
        } catch (Exception e) {
            System.out.println("Wrong Input! Please try again.");
            viewSpecificOrder(sc);
        }
        admDAO.showSpecificOrder(pick);
    }

    @Override
    public void viewCustomers() {
        System.out.println();
        admDAO.viewCustomers();
    }
}