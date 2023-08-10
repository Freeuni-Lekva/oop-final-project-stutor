import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlAccountsDAO {
    String username = "root";
    String password = "apokalips";
    String url = "jdbc:mysql://localhost:3306/stutor_db";
    private BasicDataSource ds = null;

    public SqlAccountsDAO() {
        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/stutor_db");
        ds.setUsername("root");
        ds.setPassword("apokalips");
    }

    // adds Account into the users table
    public void addAccount(Account account) {
        // TO DO
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO users (username, password, firstname, lastname, role_id) VALUES (");
            sb.append("'").append(account.getUsername()).append("' ,");
            sb.append("'").append(account.getUserPassword()).append("' ,");
            sb.append("'").append(account.getFirstName()).append("' ,");
            sb.append("'").append(account.getLastName()).append("' ,");
            sb.append("'").append(account.getUserRoles()).append("' );");
            statement.executeUpdate(sb.toString());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Username is already in use");;
        }
    }

    // removes all users from the users table
    public void removeAllUsers() {
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users;");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("error deleting all users");
        }
    }

    // removes the Account from the table
    public void removeAccount(String username){
        // TO DO
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("DELETE FROM users WHERE ");
            sb.append("username = '").append(username).append("';");
            statement.executeUpdate(sb.toString());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("No such user to remove");
        }

    }

    // search username and return list of Accounts
    public ArrayList<Account> searchUsernameLike(String username){
        ArrayList<Account> searchedUsers = new ArrayList<>();
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("SELECT * FROM users WHERE ");
            sb.append("username like '%").append(username).append("%';");
            ResultSet rs = statement.executeQuery(sb.toString());
            while(rs.next()) {
                Account account = prepareAccount(rs);
                searchedUsers.add(account);
            }
            rs.close();
            statement.close();
            connection.close();
            return searchedUsers;
        } catch (SQLException e) {
            System.out.println("Search error");
        }
        return searchedUsers;
    }

    // gets java Account object from the table of users
    public Account getAccount(String username){
        Account account = null;
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("SELECT * FROM users WHERE ");
            sb.append("username = '").append(username).append("';");
            ResultSet rs = statement.executeQuery(sb.toString());
            rs.next();
            account = prepareAccount(rs);

            rs.close();
            statement.close();
            connection.close();
            return account;
        } catch (SQLException e) {
            System.out.println("Search error");
        }
        return account;
    }

    // changes the password for the given user
//    public void changePassword(String username, String password){
//        try {
//            Connection connection = ds.getConnection();
//            Statement statement = connection.createStatement();
//            StringBuilder sb = new StringBuilder("UPDATE users SET ");
//            sb.append("password = '").append(password).append("' WHERE ");
//            sb.append("username = '").append(username).append("';");
//            statement.executeUpdate(sb.toString());
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("No such user to change password");
//        }
//    }

    private Account prepareAccount(ResultSet rs) throws SQLException {
        int id = rs.getInt("user_id");
        String uname = rs.getString("username");
        String password = rs.getString("password");

        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");

        int role = rs.getInt("role_id");

        Account account = new StudentAccount(uname, password, firstname, lastname, role);
        account.setUserId(id);
        return account;
    }


    // more generic update method
    public void updateUser(String username, String attribute, String value) {
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("UPDATE users SET ");
            sb.append(attribute).append(" = '").append(value).append("' WHERE ");
//            sb.append("password = '").append(password).append("' WHERE ");
            sb.append("username = '").append(username).append("';");
            statement.executeUpdate(sb.toString());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("No such user to change password");
        }
    }

}
