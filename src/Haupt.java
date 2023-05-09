public class Haupt {
    public static void main(String[] args) {
        Admin a = new Admin();
        DeliveryService service = new PizzaDeliveryAPI(a);
        PizzaConsole pc = new PizzaConsole(service);
        pc.run();
    }
}