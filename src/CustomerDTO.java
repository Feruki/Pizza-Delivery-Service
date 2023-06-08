public class CustomerDTO extends UserDTO {
    // Attributes
    private String name, surname, username, password;
    private AddressDTO address;
    private Cart shoppingcart;
    private int id;

    // Constructor
    public CustomerDTO(String n, String s, String u, String p, AddressDTO a) {
        this.name = n;
        this.surname = s;
        this.username = u;
        this.password = p;
        this.address = a;
        this.shoppingcart = new Cart();
    } 
   
    // Getter and Setter
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFullName() {
        return surname + ", " + name;
    }
    
    public AddressDTO getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public Cart getCart() {
        return shoppingcart;
    }

    //* Special Getters
    // Showing all products in the Cart
    public void showCart() {
        try {
            shoppingcart.showProducts();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Showing the total cost of the Cart
    public double showTotal() {
        try {
            return shoppingcart.getTotal();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return 0;
    } 
}