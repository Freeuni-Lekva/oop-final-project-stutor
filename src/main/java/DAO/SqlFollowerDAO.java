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
    private final static String DBNAME = "stutor_db";
    private final static String TABLENAME = "followers";


    public SqlFollowerDAO() throws ClassNotFoundException {

    }

    @Override
    public boolean addFollower(String following, String follower) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(TABLENAME).append(" (following, follower) VALUES (");
        sb.append("'").append(following).append("' ,");
        sb.append("'").append(follower).append("');");

        int check = statement.executeUpdate(sb.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return check == 1;
    }

    @Override
    public boolean removeFollower(String following, String follower) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").append(TABLENAME).append(" WHERE ");
        sb.append("following = '").append(following).append("' AND follower = '").append(follower).append("';");

        int check = statement.executeUpdate(sb.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return check == 1;
    }

    @Override
    public List<User> getFollowers(String user) throws SQLException {
        List<User> followers = new ArrayList<>();

        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT users.* FROM users JOIN ").append(TABLENAME).append(" AS f ON users.username = f.follower ");
        sb.append(" WHERE f.following = '").append(user).append("';");

        ResultSet rs = statement.executeQuery(sb.toString());

        while(rs.next()) {
            followers.add(new User(rs.getString("username"), rs.getString("hashedpassword"),
                    rs.getString("firstname"), rs.getString("lastname"),
                    rs.getString("email")));
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return followers;
    }

    @Override
    public List<User> getFollowings(String user) throws SQLException {
        List<User> followings = new ArrayList<>();

        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT users.* FROM users JOIN ").append(TABLENAME).append(" AS f ON users.username = f.following ");
        sb.append(" WHERE f.follower = '").append(user).append("';");

        ResultSet rs = statement.executeQuery(sb.toString());

        while (rs.next()) {
            followings.add(new User(rs.getString("username"), rs.getString("hashedpassword"),
                    rs.getString("firstname"), rs.getString("lastname"),
                    rs.getString("email")));
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return followings;
    }

    @Override
    public boolean isFollowing(String follower, String following) throws SQLException {
        boolean res = false;
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder code = new StringBuilder();

        statement.execute("USE " + DBNAME + ";\n");

        code.append("select * from followers where follower = '");
        code.append(follower).append("' and following = '").append(following);
        code.append("';");

        ResultSet rs = statement.executeQuery(code.toString());
        while(rs.next()){
            res = true;
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return res;
    }
}
