import java.sql.*;

public class AddressDAOImpl implements AddressDAO {
    // Attributes
    private Connection connection;

    // Constructor
    public AddressDAOImpl(Connection conn) {
        this.connection = conn;
    }

    // Saves a new entry in the addresses table of the database
    @Override
    public void saveAddress(AddressDTO addr) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("INSERT INTO addresses (city, street, zipcode, house_number) VALUES (?, ?, ?, ?)");
            pStatement.setString(1, addr.getCity());
            pStatement.setString(2, addr.getStreet());
            pStatement.setInt(3, addr.getZipcode());
            pStatement.setInt(4, addr.getNumber());
            pStatement.executeUpdate();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    // Overrides an existing entry in the adddresses table of the database
    @Override
    public void updateAddress(CustomerDTO customer, AddressDTO addr) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("UPDATE addresses SET city = ?, street = ?, zipcode = ?, house_number = ? WHERE (SELECT address_id FROM customers WHERE customerID = ?)");
            pStatement.setString(1, addr.getCity());
            pStatement.setString(2, addr.getStreet());
            pStatement.setInt(3, addr.getZipcode());
            pStatement.setInt(4, addr.getNumber());
            pStatement.setInt(5, customer.getId());
            pStatement.executeUpdate();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }
}