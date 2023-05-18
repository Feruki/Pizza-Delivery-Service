@SuppressWarnings("unused") // username and password aren't used in an abstract class, just so the IDE doesn't show it anymore, changes nothing about the program
public abstract class User {
    // Attributes
    private String username, password;

    // Head of login method
    abstract boolean login(String u, String p);
}