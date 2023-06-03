import java.sql.*;

import org.mindrot.jbcrypt.BCrypt;

public class AdminDAOImpl implements AdminDAO, UserDAO {
    // Attributes
    private Connection connection;

    public AdminDAOImpl(Connection conn) {
        this.connection = conn;
    }

    @Override
    public boolean login(String u, String p) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT username, pw_hash FROM customers WHERE customerID = 0");
            ResultSet result = pStatement.executeQuery();

            String username = result.getString("username");
            String hashedPw = result.getString("pw_hash");

            if(u.equals(username) && BCrypt.checkpw(p, hashedPw)) {
                return true;
            } else return false;
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
            return false;
        }
    }

    @Override
    public void addProduct(ProductDTO prod) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO products (productName, productPrice, productType) VALUES (?, ?, ?)");
            pStatement.setString(1, prod.getName());
            pStatement.setDouble(2, prod.getPrice());
            pStatement.setString(3, prod.getType());
            pStatement.executeUpdate();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    @Override
    public void removeProduct(int pID) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("DELETE FROM menu WHERE menu_productID = ?");
            pStatement.setInt(1, pID);
            pStatement.executeUpdate();
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    @Override
    public void updatePrice(int pID, double price) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("UPDATE products SET price = ? WHERE id = ?");
            pStatement.setDouble(1, price);
            pStatement.setInt(2, pID);
            pStatement.executeUpdate();
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }
    
}
