public interface CustomerDAO {
    public int saveCustomer(CustomerDTO c, AddressDAOImpl aDAO);
    public CustomerDTO getCustomerById(int id);
    public CustomerDTO getCustomerByUsername(String u);
    public void addToCart(ProductDTO p, int quant, CustomerDTO c);
    public void removeFromCart(ProductDTO p, int quant, CustomerDTO c);
    public void showCart(CustomerDTO c);
    public double showTotal(CustomerDTO c);
    public void changeAddress(AddressDAOImpl aDAO, AddressDTO addr, CustomerDTO c);
    public void placeOrder(CustomerDTO c);
    public void showOrders(CustomerDTO c);
    public void showSpecificOrder(CustomerDTO c, int oID);
}
