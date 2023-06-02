import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Haupt {
    private static final String CONFIG_FILE = "config.properties";
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(getProperty("db.url"), getProperty("db.username"), getProperty("db.password"));

            AddressDAOImpl aDAO = new AddressDAOImpl(con);
            ProductDAOImpl pDAO = new ProductDAOImpl(con);
            //! TEMP
            aDAO.getClass();
            pDAO.getClass();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Admin a = new Admin();
        DeliveryService service = new DeliveryServiceImpl(a);
        PizzaConsole pc = new PizzaConsole(service);
        pc.run();
    }

    private static String getProperty(String propertyName) {
        Properties prop = new Properties();
        try (InputStream is = new FileInputStream(CONFIG_FILE);) {
            prop.load(is);
            return prop.getProperty(propertyName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}