import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class AdminDAOImpl implements AdminDAO, UserDAO {
    // Attributes
    private Connection connection;

    // Constructor
    public AdminDAOImpl(Connection conn) {
        this.connection = conn;
    }

    // Admin login method
    @Override
    public boolean login(String u, String p) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT pw_hash FROM customers WHERE customerID = 0");
            ResultSet result = pStatement.executeQuery();

            if(result.next()) {
                if(u.equals("admin") && BCrypt.checkpw(p, result.getString("pw_hash"))) return true;
            }
        } catch (SQLException SQLe) {
            SingleLogger.getLogger().error("Error in the database query", SQLe);
        }
        return false;
    }

    // Adding a product to the products table in the database
    @Override
    public void addProduct(ProductDTO prod) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO products (productName, productPrice, productType) VALUES (?, ?, ?)");
            pStatement.setString(1, prod.getName());
            pStatement.setDouble(2, prod.getPrice());
            pStatement.setString(3, prod.getType());
            pStatement.executeUpdate();
        } catch (SQLException SQLe) {
            SingleLogger.getLogger().error("Error in the database query", SQLe);
        }
    }

    // Removing a product from the products table in the database
    @Override
    public void removeProduct(int pID) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM menu WHERE menu_productID = ?");
            pStatement.setInt(1, pID);
            pStatement.executeUpdate();
        } catch(SQLException SQLe) {
            SingleLogger.getLogger().error("Error in the database query", SQLe);
        }
    }

    // Updating the price of a prodt in the products table from the database
    @Override
    public void updatePrice(int pID, double price) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("UPDATE products SET productPrice = ? WHERE productID = ?");
            pStatement.setDouble(1, price);
            pStatement.setInt(2, pID);
            pStatement.executeUpdate();
        } catch(SQLException SQLe) {
            SingleLogger.getLogger().error("Error in the database query", SQLe);
        }
    }

    // Printing out every order made, pulled from the orders table in the database
    @Override
    public void viewOrders() {
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = pStatement.executeQuery();

            while(resultSet.next()) {
                int orderId = resultSet.getInt("orderID");
                double totalCost = resultSet.getDouble("total_cost");
                int customerId = resultSet.getInt("customer_id");
                
                System.out.println("Order #" + orderId + " - Total Cost: " + totalCost + " Order from Customer #" + customerId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showSpecificOrder(int oID) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM orders WHERE orderID = ?");
            pStatement.setInt(1, oID);

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
            SingleLogger.getLogger().error("Error in the database query", SQLe);
        }
    }
    
    // Printing out every customer registed, pulled from the customers table in the database
    @Override
    public void viewCustomers() {
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM customers");
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                int customerId = resultSet.getInt("customerID");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                
                System.out.println("Customer #" + customerId + " - " + surname + ", " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}