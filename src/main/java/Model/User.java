package Model;

import java.util.Objects;

public class User{
    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String hashedPassword;

    public User(String username, String hashedPassword, String firstname, String lastname, String email){
        this.username = username;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof User)) return false;

        User a = (User)other;
        return Objects.equals(a.username, this.username);
    }
}
