package Setup;

public class UserModel {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String phonenumber;
    private String address;


// Getter Setter method

// ctrl + . for short the full Setter & Getter
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//  Encapsulation
    public UserModel(String firstname, String lastname, String email, String password, String phonenumber, String address){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.address = address;
    }

// to solve issue in RegistrationTestRunner page userModel -- We have created and empty UserModel object to overload constructor.
    public UserModel(){

    }
}
