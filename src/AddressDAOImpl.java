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
    public int saveAddress(AddressDTO addr) {
        int aID = -1;
        try {
            // Check if the address already exists in the database
            PreparedStatement checkStatement = connection.prepareStatement("SELECT addressID FROM addresses WHERE city = ? AND street = ? AND zipcode = ? AND house_number = ?");
            checkStatement.setString(1, addr.getCity());
            checkStatement.setString(2, addr.getStreet());
            checkStatement.setInt(3, addr.getZipcode());
            checkStatement.setInt(4, addr.getNumber());

            ResultSet checkResult = checkStatement.executeQuery();
            
            // Address already exists
            if(checkResult.next()) {
                aID = checkResult.getInt(1);
                SingleLogger.getLogger().warn("Address already exists. ID {} returned", aID);
            }
            // Address doesn't exist, so adding a new one
            else {
                PreparedStatement pStatement = connection.prepareStatement("INSERT INTO addresses (city, street, zipcode, house_number) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                pStatement.setString(1, addr.getCity());
                pStatement.setString(2, addr.getStreet());
                pStatement.setInt(3, addr.getZipcode());
                pStatement.setInt(4, addr.getNumber());
                pStatement.executeUpdate();
    
                ResultSet genKeys = pStatement.getGeneratedKeys();
                if(genKeys.next()) {
                    aID = genKeys.getInt(1);
                }
                SingleLogger.getLogger().info("Successfully added the Address {}", aID);
            }
        } catch (SQLException SQLe) {
            SingleLogger.getLogger().error("Error in the database query", SQLe);
        }
        return aID;
    }

    // Overrides an existing entry in the adddresses table of the database
    @Override
    public void updateAddress(CustomerDTO customer, AddressDTO addr) {
        try {
            PreparedStatement pStatement = connection.prepareStatement("UPDATE addresses SET city = ?, street = ?, zipcode = ?, house_number = ? WHERE addressID = (SELECT address_id FROM customers WHERE customerID = ?)");
            pStatement.setString(1, addr.getCity());
            pStatement.setString(2, addr.getStreet());
            pStatement.setInt(3, addr.getZipcode());
            pStatement.setInt(4, addr.getNumber());
            pStatement.setInt(5, customer.getId());
            pStatement.executeUpdate();
            SingleLogger.getLogger().info("Address successfully updated");
        } catch (SQLException SQLe) {
            SingleLogger.getLogger().error("Error in the database query", SQLe);
        }
    }
}