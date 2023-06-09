public interface AdminDAO {
    public void addProduct(ProductDTO prod);
    public void removeProduct(int pID);
    public void updatePrice(int pID, double price);
    public void viewOrders();
    public void showSpecificOrder(int oID);
    public void viewCustomers();
}
