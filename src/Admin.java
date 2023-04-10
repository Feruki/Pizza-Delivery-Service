public class Admin extends User{
    //attributes
    private String username, password;
    
    //constructor
    Admin() {
        username = "admin";
        password = "adminPw";
    }

    //login with admin credentials
    @Override
    Boolean login(String u, String p) {
        if(u.equals(username) && p.equals(password)){
            return true;
        }
        return false;
    }


    //admin methods

    //add new product
    void addProduct(Product p){
        //add to List

    }
    //remove product
    void deleteProduct(Product p){
        //remove from List
    }
    
}
