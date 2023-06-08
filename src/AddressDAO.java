public interface AddressDAO {
    public int saveAddress(AddressDTO addr);
    public void updateAddress(CustomerDTO customer, AddressDTO addr);
}