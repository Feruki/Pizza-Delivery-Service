public interface ProductDAO {
    void saveProduct(ProductDTO product);
    void updatePrice(int pID, double price);
    void deleteProduct(int pID);
}