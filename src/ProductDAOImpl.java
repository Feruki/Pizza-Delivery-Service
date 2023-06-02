import java.sql.*;

public class ProductDAOImpl implements ProductDAO {
    // Attributes
    private Connection connection;

    // Constructor
    public ProductDAOImpl(Connection conn) {
        this.connection = conn;
    }

    // Saves a new entry in the products table of the database
    @Override
    public void saveProduct(ProductDTO product) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("INSER INTO products (productName, productPrice, productType) VALUES (?, ?, ?)");
            pStatement.setString(1, product.getName());
            pStatement.setDouble(2, product.getPrice());
            pStatement.setString(3, product.getType());
            pStatement.executeUpdate();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    // Overrides the price column in the products table of the database
    @Override
    public void updatePrice(int pID, double price) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("SET productPrice = ? WHERE productID = ?");
            pStatement.setDouble(1, price);
            pStatement.setInt(2, pID);
            pStatement.executeUpdate();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    // Removes an entry in the products table of the database
    @Override
    public void deleteProduct(int pID) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM menu WHERE menu_productID = ?");
            pStatement.setInt(1, pID);
            pStatement.executeUpdate();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }
    
}
