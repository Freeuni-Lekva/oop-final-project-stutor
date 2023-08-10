import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlFriendsDAO {

    String username = "root";
    String password = "apokalips";
    String url = "jdbc:mysql://localhost:3306/stutor_db";
    private BasicDataSource ds = null;

    public SqlFriendsDAO() {
        ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:3306/stutor_db");
        ds.setUsername("root");
        ds.setPassword("apokalips");
    }


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


    // add friend into the friends table
    public void addFriend(int userId, int friendId) {
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO friends (user_id, friend_id) VALUES (");
            sb.append("'").append(userId).append("' ,");
            sb.append("'").append(friendId).append("');");

            statement.executeUpdate(sb.toString());

            sb = new StringBuilder("INSERT INTO friends (user_id, friend_id) VALUES (");
            sb.append("'").append(friendId).append("' ,");
            sb.append("'").append(userId).append("');");

            statement.executeUpdate(sb.toString());

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Friend not found");;
        }

    }


    public void removeFriend(int userId, int friendId) {
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("DELETE FROM friends WHERE ");
            sb.append("user_id = ").append(userId).append(" AND friend_id = ").append(friendId).append(";");

            statement.executeUpdate(sb.toString());

            sb = new StringBuilder("DELETE FROM friends WHERE ");
            sb.append("user_id = ").append(friendId).append(" AND friend_id = ").append(userId).append(";");

            statement.executeUpdate(sb.toString());

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't remove friend");
        }
    }


    // returns list of friends for the current user
    public List<Account> getFriends(int userId) {
        List<Account> friends = new ArrayList<>();
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("SELECT users.* FROM users JOIN friends ON users.user_id = friends.friend_id ");
            sb.append(" WHERE friends.user_id = ").append(userId).append(";");

            ResultSet rs = statement.executeQuery(sb.toString());

            while(rs.next()) {
                Account account = prepareAccount(rs);
                friends.add(account);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getting friends");
        }
        return friends;
    }


    // for test purposes
//    public void clearFriendsTable() {
//        try {
//            Connection connection = ds.getConnection();
//            Statement statement = connection.createStatement();
//            statement.executeUpdate("DELETE FROM friends;");
//            statement.close();
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("error deleting all friends");
//        }
//    }
}
