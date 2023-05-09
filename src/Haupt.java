public class Haupt {
    public static void main(String[] args) {
        Admin a = new Admin();
        DeliveryService dS = new PizzaDeliveryAPI(a);
        PizzaConsole pc = new PizzaConsole(dS);
        pc.run();
    }
}