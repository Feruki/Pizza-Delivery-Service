import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    // Attributes
    private Connection connection;

    // Constructor
    public ProductDAOImpl(Connection conn) {
        this.connection = conn;
    }

    // Retrieving the current menu from the database
    @Override
    public List<ProductDTO> getMenu() {
        List<ProductDTO> menu = new ArrayList<>();
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM menu");
            ResultSet products = pStatement.executeQuery();
            while(products.next()) {
                int id = products.getInt("menu_productID");
                String name = products.getString("menu_productName");
                double price = products.getDouble("menu_productPrice");
                String type = products.getString("menu_productType");

                ProductDTO prod = new ProductDTO(name, price, type);
                prod.setId(id);

                menu.add(prod);
            } 
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return menu;
    }

    // Finding a product through the specified id in the products table of the database
    @Override
    public ProductDTO getProductById(int id) {
        ProductDTO p = null;
        try {
            PreparedStatement pStatement = connection.prepareStatement("SELECT * FROM products WHERE productID = ?");
            pStatement.setInt(1, id);

            ResultSet prod = pStatement.executeQuery();

            if(prod.next()) {
                String name = prod.getString("productName");
                Double price = prod.getDouble("productPrice");
                String type = prod.getString("productType");
    
                p = new ProductDTO(name, price, type);
                p.setId(id);
            }  
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return p;
    }
}
