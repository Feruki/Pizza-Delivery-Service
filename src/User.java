@SuppressWarnings("unused")
public abstract class User {
    //attributes
    private String username, password;

    //head of login method
    abstract boolean login(String u, String p);
}