import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class Haupt {
    private static final String CONFIG_FILE = "config.properties";
    private static AddressDAOImpl addrDAO;
    private static AdminDAOImpl admDAO;
    private static CustomerDAOImpl cusDAO;
    private static ProductDAOImpl prdDAO;
    private static Connection con;
    public static void main(String[] args) {
        try {
            con = DriverManager.getConnection(getProperty("db.url"), getProperty("db.username"), getProperty("db.password"));

            addrDAO = new AddressDAOImpl(con);
            admDAO = new AdminDAOImpl(con);
            cusDAO = new CustomerDAOImpl(con);
            prdDAO = new ProductDAOImpl(con);
        } catch (Exception e) {
            e.printStackTrace();
        }     
        
        DeliveryService service = new DeliveryServiceImpl(addrDAO, admDAO, cusDAO, prdDAO);
        PizzaConsole pc = PizzaConsole.guiInstance(service);
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