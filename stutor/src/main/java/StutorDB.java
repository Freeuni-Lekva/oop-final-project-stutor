
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    public void clearFriendsTable() {
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM friends;");
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("error deleting all friends");
        }
    }


    public void addRating (int user_id, int rated_id, int rating) {
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO ratings (user_id, rated_id, rating_value) VALUES (");
            sb.append(user_id).append(" ,");
            sb.append(rated_id).append(" ,");
            sb.append(rating).append(");");
            statement.executeUpdate(sb.toString());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error adding rating");;
        }
    }

    public double getRating(int user_id) {
        int count = 0;
        int sum = 0;
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("SELECT * FROM ratings ");
            sb.append(" WHERE rated_id = ").append(user_id).append(";");

            ResultSet rs = statement.executeQuery(sb.toString());

            while(rs.next()) {
                count++;
                sum = sum + rs.getInt("rating_value");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error calculating rating");
        }

        if (count == 0) {
            return 0;
        }
        return (double) sum / count;
    }


    public void addSubject(int user_id, String sub_id) {
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("INSERT INTO tutor_subjects (user_id, sub_id) VALUES (");
            sb.append(user_id).append(" , '");
            sb.append(sub_id).append("');");
            statement.executeUpdate(sb.toString());
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error adding subject");;
        }
    }


    public void removeSubject(int user_id, String sub_id) {
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("DELETE FROM tutor_subjects WHERE ");
            sb.append("user_id = ").append(user_id).append(" AND sub_id = '").append(sub_id).append("';");
            statement.executeUpdate(sb.toString());

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't remove subject");
        }
    }


    public List<String> getSubjects(int user_id) {
        List<String> subjects = new ArrayList<>();
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("select s.subject_name from tutor_subjects as ts\n" +
                    "JOIN subjects as s ON ts.sub_id = s.sub_id");
            sb.append(" WHERE ts.user_id = ").append(user_id).append(";");

            ResultSet rs = statement.executeQuery(sb.toString());

            while(rs.next()) {
                String subject = rs.getString("subject_name");
                subjects.add(subject);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getting subjects");
        }
        return subjects;
    }

    public List<String> getAllSubjectIds() {
        List<String> subIds = new ArrayList<>();
        try {
            Connection connection = ds.getConnection();
            Statement statement = connection.createStatement();
            StringBuilder sb = new StringBuilder("select * from subjects;");
            ResultSet rs = statement.executeQuery(sb.toString());

            while(rs.next()) {
                String subId = rs.getString("sub_id");
                String subName = rs.getString("subject_name");
                subIds.add(subId + " - " + subName);
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error getting subject Ids");
        }
        return subIds;
    }



    public static void main(String[] args) {
        StutorDB stutorDB = new StutorDB();
        List<String> list = stutorDB.getAllSubjectIds();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(stutorDB.getAllSubjectIds().get(i));
        }

//        stutorDB.addAccount(new StudentAccount("dvala", "123", "luka", "dvaladze",1));
//        stutorDB.addAccount(new StudentAccount("dvali", "321", "luka", "dvaladze",1));
//        stutorDB.addAccount(new StudentAccount("beka", "111", "beka", "dvaladze",1));

//        stutorDB.addRating(stutorDB.getAccount("dvala").getUserId(), stutorDB.getAccount("dvali").getUserId(), 4);
//        stutorDB.addRating(stutorDB.getAccount("dvala").getUserId(), stutorDB.getAccount("beka").getUserId(), 5);

//        stutorDB.addRating(stutorDB.getAccount("dvali").getUserId(), stutorDB.getAccount("beka").getUserId(), 4);

//        Account account = stutorDB.getAccount("dvala");
//        System.out.println(account.getUserPassword());

//        ArrayList<Account> ans = stutorDB.searchUsernameLike("dv");
//        for (int i = 0; i < ans.size(); i++) {
//            System.out.println(ans.get(i).getUsername());
//        }
//        Account account = new StudentAccount("beka", "111", 1);
//        stutorDB.addAccount(account);
//        stutorDB.removeAccount(account);
    }
}
