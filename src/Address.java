import java.io.Serializable;

public class Address implements Serializable {
    //attributes
    private String city, street;
    private int zipcode, number;

    //constructor
    Address(String c, String s, int z, int number){
        this.city = c;
        this.street = s;
        this.zipcode = z;
        this.number = number;
    }

    //getter and setter
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public int getZipcode() {
        return zipcode;
    }
    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}