package Model;

import java.util.Objects;

public class User{
    private int userId;
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

    public int getUserId() {
        return userId;
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

    public void setUserId(int userId){
        this.userId = userId;
    }

    @Override
    public boolean equals(Object other) {
        // standard two checks for equals()
        if (this == other) return true;
        if (!(other instanceof User)) return false;

        // check if other point same as us
        User a = (User)other;
        return Objects.equals(a.email, this.email);
    }
}