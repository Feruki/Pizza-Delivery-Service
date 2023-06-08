import java.util.List;

public interface ProductDAO {
    public List<ProductDTO> getMenu();
    public ProductDTO getProductById(int id);
}
