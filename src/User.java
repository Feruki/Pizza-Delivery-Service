public abstract class User {
    //attributes
    private String username, password;

    //head of login method
    abstract Boolean login(String u, String p);
}