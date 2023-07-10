public interface Account {
    //this method returns users id
    public int getUserId();

    //this method returns users username
    public String getUsername();

    //this method return users firstname
    public String getFirstName();

    //this method returns users lastname
    public String getLastName();

    //this method returns users password
    public String getUserPassword();

    //this method return users roles
    public int getUserRoles();

    //this method changes users usernmae
    public void setUsername(String username);

    //this method changes users firstname
    public void setFirstName(String firstname);

    //this method changes users lastname
    public void setLastName(String lastname);

    //this method changes users password
    public void setPassword(String password);

    //this method changes users roles
    public void setUserRoles(int userRoles);
}
