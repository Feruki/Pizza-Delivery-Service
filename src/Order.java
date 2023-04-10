public class Order {
    //attributes
    final int id;
    private static int idCounter = 0;

    //constructor
    Order(){
        this.id = idCounter;
        idCounter++;
    }
}
