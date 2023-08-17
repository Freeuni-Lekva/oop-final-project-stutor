package DAO;

import DAO.Interfaces.FollowerDAO;
import Model.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlFollowerDAO implements FollowerDAO {

    private final static String USERNAME = "root";
    private final static String PASSWORD = "Ikako2525";
    private final static String DBNAME = "stutor_db";
    private final static String TABLENAME = "followers";

    BasicDataSource dataSource;

    public SqlFollowerDAO() throws ClassNotFoundException {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + DBNAME);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    @Override
    public boolean addFollower(int user_id, int follower_id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(TABLENAME).append(" (user_id, follower_id) VALUES (");
        sb.append("'").append(user_id).append("' ,");
        sb.append("'").append(follower_id).append("');");

        int check = statement.executeUpdate(sb.toString());

        statement.close();
        connection.close();

        return check == 1;
    }

    @Override
    public boolean removeFollower(int user_id, int follower_id) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").append(TABLENAME).append(" WHERE ");
        sb.append("user_id = ").append(user_id).append(" AND follower_id = ").append(follower_id).append(";");

        int check = statement.executeUpdate(sb.toString());

        statement.close();
        connection.close();

        return check == 1;
    }

    @Override
    public List<User> getFollowers(int user_id) throws SQLException {
        List<User> followers = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT users.* FROM users JOIN ").append(TABLENAME).append(" AS f ON users.user_id = f.follower_id ");
        sb.append(" WHERE f.user_id = ").append(user_id).append(";");

        ResultSet rs = statement.executeQuery(sb.toString());

        while(rs.next()) {
            followers.add(new User(rs.getString("username"), rs.getString("hashedpassword"),
                    rs.getString("firstname"), rs.getString("lastname"),
                    rs.getString("email")));
        }

        statement.close();
        connection.close();

        return followers;
    }
}
