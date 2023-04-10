import java.util.ArrayList;

public class Haupt {
    
    
    public static void main(String[] args) {
        Admin a = new Admin();
        DeliveryService dS = new PizzaDeliveryAPI(a, baseMenu());
        PizzaConsole pc = new PizzaConsole(dS);
        pc.run();
    }

    public static ArrayList<Product> baseMenu() {

        ArrayList<Product> menu;
        menu = new ArrayList<Product>();

        menu.add(new Product("Pizza Margherita", 8.99, "pizza"));
        menu.add(new Product("Pizza Salami", 12.99, "pizza"));
        menu.add(new Product("Pizza Rucola", 11.99, "pizza"));
        menu.add(new Product("Pizza Tuna", 9.99, "pizza"));
        menu.add(new Product("Pizza Salsiccia", 14.99, "pizza"));
        menu.add(new Product("Pizza Mozarella", 10.99, "pizza"));
        menu.add(new Product("Pizza Prosciutto", 11.99, "pizza"));
        menu.add(new Product("Pizza Funghi", 9.99, "pizza"));
        menu.add(new Product("Pizza Pepperoni", 11.99, "pizza"));
        menu.add(new Product("Pizza Napoli", 10.99, "pizza"));
        menu.add(new Product("Pizza Frutti di Mare", 13.99, "pizza"));

        menu.add(new Product("Spaghetti aglio e olio", 12.99, "pasta"));
        menu.add(new Product("Spaghetti Carbonara", 14.99, "pasta"));
        menu.add(new Product("Spaghetti Bolognese", 11.99, "pasta"));
        menu.add(new Product("Penne al forno", 10.99, "pasta"));
        menu.add(new Product("Lasagne", 9.99, "pasta"));
        menu.add(new Product("Penne Arabiata", 11.99, "pasta"));
        menu.add(new Product("Tagliatelle Italia", 8.99, "pasta"));


        return menu;
    }
}