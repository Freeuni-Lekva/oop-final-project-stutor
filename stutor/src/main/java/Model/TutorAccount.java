package Model;

public class TutorAccount implements Account{
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int userRoles;
    private final int userId;

    public TutorAccount(String username, String password, String firstname, String lastname, int userRoles, int userId){
        this.username = username;
        this.password = password;
        this.userRoles = userRoles;
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return this.username;
    }

    public String getFirstName() {
        return this.firstname;
    }

    public String getLastName() {
        return this.lastname;
    }

    public String getUserPassword() {
        return this.password;
    }

    public int getUserRoles() {
        return this.userRoles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRoles(int userRoles) {
        this.userRoles = userRoles;
    }

}
