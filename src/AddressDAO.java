public interface AddressDAO {
    void saveAddress(AddressDTO addr);
    void updateAddress(CustomerDTO customer, AddressDTO addr);
}
