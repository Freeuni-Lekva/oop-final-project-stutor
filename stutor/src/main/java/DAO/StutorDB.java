package DAO;

import Model.Account;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StutorDB {
    String username = "root";
    String password = "apokalips";
    String url = "jdbc:mysql://localhost:3306/stutor_db";
    private BasicDataSource ds = null;

    public StutorDB() {
        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/stutor_db");
        ds.setUsername("root");
        ds.setPassword("apokalips");
    }

    public void addAccount(Account account) {
        // TO DO
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO users (username, password, role_id) VALUES (");
            sb.append("'").append(account.getUsername()).append("' ,");
            sb.append("'").append(account.getPassword()).append("' ,");
            sb.append("'").append(account.getUserTypes()).append("' );");
            statement.executeUpdate(sb.toString());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Username is already in use");;
        }
    }

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

    public void removeAccount(Account account){
        // TO DO
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("DELETE FROM users WHERE ");
            sb.append("username = '").append(account.getUsername()).append("';");
            statement.executeUpdate(sb.toString());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("No such user to remove");
        }

    }

    // search username and return list of ids
    public ArrayList<Account> searchUsernameLike(String username){
        ArrayList<Account> searchedUsers = new ArrayList<>();
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("SELECT * FROM users WHERE ");
            sb.append("username like '%").append(username).append("%';");
            ResultSet rs = statement.executeQuery(sb.toString());
            while(rs.next()) {
                int id = rs.getInt("user_id");
                String uname = rs.getString("username");
                String password = rs.getString("password");
                int role = rs.getInt("role_id");
                Account account = new StudentAccount(uname, password, role);
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

    public Account getAccount(String username){
        Account account = null;
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("SELECT * FROM users WHERE ");
            sb.append("username = '").append(username).append("';");
            ResultSet rs = statement.executeQuery(sb.toString());
            rs.next();
            int id = rs.getInt("user_id");
            String uname = rs.getString("username");
            String password = rs.getString("password");
            int role = rs.getInt("role_id");
            account = new StudentAccount(uname, password, role);

            rs.close();
            statement.close();
            connection.close();
            return account;
        } catch (SQLException e) {
            System.out.println("Search error");
        }
        return account;
    }

    public void changePassword(String username, String password){
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("UPDATE users SET ");
            sb.append("password = '").append(password).append("' WHERE ");
            sb.append("username = '").append(username).append("';");
            statement.executeUpdate(sb.toString());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("No such user to change password");
        }
    }


    // For later
//    public ArrayList<Account> search(int role, ArrayList<String> subject, int minRating, int maxRating, String username){
//
//        return null;
//    }




    public static void main(String[] args) {
        StutorDB stutorDB = new StutorDB();
//        stutorDB.addAccount(new StudentAccount("dvali", "123", 1));
        Account account = stutorDB.getAccount("dvala");
        System.out.println(account.getPassword());

//        ArrayList<Account> ans = stutorDB.searchUsernameLike("dv");
//        for (int i = 0; i < ans.size(); i++) {
//            System.out.println(ans.get(i).getUsername());
//        }
//        Account account = new StudentAccount("beka", "111", 1);
//        stutorDB.addAccount(account);
//        stutorDB.removeAccount(account);
    }
}
