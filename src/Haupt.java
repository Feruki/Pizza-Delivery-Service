import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import org.mindrot.jbcrypt.BCrypt;

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

        //! TEMP
        String salt = "$2a$10$br3Xpu/sf18KRNf52h51X.";
        String hashedPw = BCrypt.hashpw("admin", salt);
        System.out.println(salt + " <- Salt; Hash -> " + hashedPw);
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