import java.sql.*;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

public class CustomerDAOImpl implements CustomerDAO, UserDAO {
    // Attributes
    private Connection connection;

    // Constructor
    public CustomerDAOImpl(Connection conn) {
        this.connection = conn;
    }

    // Saves a new entry in the customers table of the database
    @Override
    public int saveCustomer(CustomerDTO c, AddressDAOImpl aDAO) {
        int cID = -1;
        int aID = aDAO.saveAddress(c.getAddress());
        try {
            PreparedStatement checkStatement = connection.prepareStatement("SELECT customerID FROM customers WHERE username = ?");
            checkStatement.setString(1, c.getUsername());

            ResultSet checkResult = checkStatement.executeQuery();

            if(checkResult.next()) {
                System.out.println("Username already exists!");
                return -1;
            }

            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO customers (name, surname, username, pw_hash, address_id) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            pStatement.setString(1, c.getName());
            pStatement.setString(2, c.getSurname());
            pStatement.setString(3, c.getUsername());
            pStatement.setString(4, BCrypt.hashpw(c.getPassword(), BCrypt.gensalt()));
            pStatement.setInt(5, aID);
            pStatement.executeUpdate();

            ResultSet genKeys = pStatement.getGeneratedKeys();
            if(genKeys.next()) {
                cID = genKeys.getInt(1);
            }
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return cID;
    }

    // Returns a CustomerDTO object by searching the customers table for the specified id
    @Override
    public CustomerDTO getCustomerById(int id) {
        CustomerDTO c = null;
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM customers WHERE customerID = ?");
            pStatement.setInt(1, id);

            ResultSet customer = pStatement.executeQuery();

            if(customer.next()) {
                String name = customer.getString("name");
                String surname = customer.getString("surname");
                String username = customer.getString("username");
                String hashPw = customer.getString("pw_hash");
                int aID = customer.getInt("address_id");
    
                PreparedStatement addrStatement = connection.prepareStatement("SELECT * FROM addresses WHERE addressID = ?");
                addrStatement.setInt(1, aID);
    
                ResultSet address = addrStatement.executeQuery();
    
                if(address.next()) {
                    String city = address.getString("city");
                    String street = address.getString("street");
                    int zipcode = address.getInt("zipcode");
                    int house_number = address.getInt("house_number");
        
                    c = new CustomerDTO(name, surname, username, hashPw, new AddressDTO(city, street, zipcode, house_number));
                    c.setId(id);
                }
            }
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return c;
    }

    // Returns a CustomerDTO object by searching the customers table for the specified username
    @Override
    public CustomerDTO getCustomerByUsername(String u) {
        CustomerDTO c = null;
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM customers WHERE username = ?");
            pStatement.setString(1, u);

            ResultSet customer = pStatement.executeQuery();

            if(customer.next()) {
                String name = customer.getString("name");
                String surname = customer.getString("surname");
                String username = customer.getString("username");
                String hashPw = customer.getString("pw_hash");
                int aID = customer.getInt("address_id");
    
                PreparedStatement addrStatement = connection.prepareStatement("SELECT * FROM addresses WHERE addressID = ?");
                addrStatement.setInt(1, aID);
    
                ResultSet address = addrStatement.executeQuery();
    
                if(address.next()) {
                    String city = address.getString("city");
                    String street = address.getString("street");
                    int zipcode = address.getInt("zipcode");
                    int house_number = address.getInt("house_number");
        
                    c = new CustomerDTO(name, surname, username, hashPw, new AddressDTO(city, street, zipcode, house_number));
                    c.setId(customer.getInt("customerID"));
                }
            }
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return c;
    }

    // Customer login method
    @Override
    public boolean login(String u, String p) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT pw_hash FROM customers WHERE username = ?");
            pStatement.setString(1, u);
            ResultSet result = pStatement.executeQuery();
            if(result.next()) {
                if(u.equals(u) && BCrypt.checkpw(p, result.getString("pw_hash"))) return true;
            }
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return false;
    }

    // Adding the selected product to the cart based on the quantity
    @Override
    public void addToCart(ProductDTO p, int quant, CustomerDTO c) {
        c.getCart().addProduct(p, quant);
    }

    // Removing the selected product to the cart based on the quantity
    @Override
    public void removeFromCart(ProductDTO p, int quant, CustomerDTO c) {
        c.getCart().removeProduct(p, quant);
    }

    // Printing the current products in the shopping cart
    @Override
    public void showCart(CustomerDTO c) {
        c.getCart().showProducts();
    }

    // Printing the current total price of the shopping cart
    @Override
    public double showTotal(CustomerDTO c) {
        return c.getCart().getTotal();
    }

    // Calling the updateAddress function from AddressDAO to update the address of a customer
    @Override
    public void changeAddress(AddressDAOImpl aDAO, AddressDTO addr, CustomerDTO c) {
        aDAO.updateAddress(c, addr);
    }

    // Placing an order with the necessary substeps
    @Override
    public void placeOrder(CustomerDTO c) {
        try {
            // Insert a new Order for the customer
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO orders (customer_id) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            pStatement.setInt(1, c.getId());
            pStatement.executeUpdate();


            // After inserting, grab the last inserted order ID
            ResultSet genKey = pStatement.getGeneratedKeys();
            int orderID = 0;
            if(genKey.next()) {
                orderID = genKey.getInt(1);
            } else {
                System.out.println("Placing the order failed, please try again later.");
                return;
            }

            PreparedStatement oStatement = connection.prepareStatement("INSERT INTO order_items (oID, pID, quantity) VALUES (?, ?, ?)");

            // Grab products from the cart and insert into order_items
            Map<ProductDTO, Integer> products = c.getCart().getProducts();

            // Iterate through the Map and set the values for each product then execute that to write it into the database
            for(Map.Entry<ProductDTO, Integer> entry : products.entrySet()) {
                ProductDTO p = entry.getKey();
                int quant = entry.getValue();

                oStatement.setInt(1, orderID);
                oStatement.setInt(2, p.getId());
                oStatement.setInt(3, quant);
                oStatement.executeUpdate();
            }

            // Clear cart after placing the order
            c.getCart().clearCart();
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    // Printing the list of orders made by a specific customers
    @Override
    public void showOrders(CustomerDTO c) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM orders WHERE customer_id = ?");
            pStatement.setInt(1, c.getId());

            ResultSet o = pStatement.executeQuery();

            while(o.next()) {
                int oID = o.getInt("orderID");
                double totalCost = o.getDouble("total_cost");

                System.out.println("Order #" + oID + " - Total Cost: " + totalCost);
            }
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }
    
    @Override
    public void showSpecificOrder(CustomerDTO c, int oID) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM orders WHERE customer_id = ? AND orderID = ?");
            pStatement.setInt(1, c.getId());
            pStatement.setInt(2, oID);

            ResultSet sO = pStatement.executeQuery();

            if(sO.next()) {
                double totalCost = sO.getDouble("total_cost");

                System.out.println("Order #" + oID + " - Total Cost: " + totalCost);

                PreparedStatement itemStatement = connection.prepareStatement("SELECT * FROM order_items WHERE oID = ?");
                itemStatement.setInt(1, oID);

                ResultSet items = itemStatement.executeQuery();

                while(items.next()) {
                    int pID = items.getInt("pID");
                    int quant = items.getInt("quantity");

                    PreparedStatement productStatement = connection.prepareStatement("SELECT * FROM products WHERE productID = ?");
                    productStatement.setInt(1, pID);

                    ResultSet products = productStatement.executeQuery();

                    if(products.next()) {
                        String productName = products.getString("productName");
                        Double productPrice = products.getDouble("productPrice");
                        
                        System.out.println(quant + "x " + productName + ": " + productPrice + "$");
                    }
                }
            } else {
                System.out.println("Order #" + oID + " not found. Please try again.");
                return;
            }
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }
}