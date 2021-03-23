package cat.tecnocampus.users.domain;

public class User {

    private String username;
    private String name;
    private String secondName;
    private String email;

    public User() { }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String toString() {
        String value = "Usuari: " + this.username + ", " + this.name + " " + this.secondName;
        return value;
    }

}