package DAO;

import DAO.Interfaces.UserDAO;
import Model.User;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlUserDAO implements UserDAO {
    private final static String DBNAME = "stutor_db";
    private final static String TABLENAME = "users";

    public SqlUserDAO() throws ClassNotFoundException {

    }


    @Override
    public boolean addUser(User user) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("INSERT INTO " + TABLENAME + " (username, hashedPassword, firstname, lastname, email) VALUES (");

        code.append("'" + user.getUsername() + "', ");
        code.append("'" + user.getHashedPassword() + "', ");
        code.append("'" + user.getFirstname() + "', ");
        code.append("'" + user.getLastname() + "', ");
        code.append("'" + user.getEmail() + "')");

        try{
            statement.executeUpdate(code.toString());
        }catch (SQLException e){
            return false;
        }


        statement.close();
        ConnectionPool.releaseConnection(connection);

        return true;

    }

    @Override
    public boolean removeUser(String username) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("DELETE FROM users WHERE ");
        code.append("username = '").append(username).append("';");

        int check = statement.executeUpdate(code.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return true;
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("select * from ").append(TABLENAME);
        code.append(" where email = '").append(email).append("';");

        return getUser(connection, statement, code);
    }

    public User getUserByUsername(String username) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("select * from ").append(TABLENAME);
        code.append(" where username = '").append(username).append("'");

        return getUser(connection, statement, code);
    }

    @Override
    public boolean isValidUser(String email, String password) throws SQLException {
        User user = getUserByEmail(email);

        if(user == null) return false;

        BCrypt.Verifyer verifier = BCrypt.verifyer();
        BCrypt.Result res = verifier.verify(password.toCharArray(), user.getHashedPassword().toCharArray());

        return res.verified;
    }

    @Override
    public boolean setPassword(String email, String newPassword) throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        BCrypt.Hasher hasher = BCrypt.withDefaults();
        char[] chars = new char[newPassword.length()];
        for(int i = 0; i < newPassword.length(); i++ ){
            chars[i] = newPassword.charAt(i);
        }
        String hashedPassword = hasher.hashToString(10, chars);

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("update ").append(TABLENAME).append(" SET hashedpassword = '");
        code.append(hashedPassword).append("' where email = '").append(email).append("';");

        int updated = statement.executeUpdate(code.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return updated == 1;
    }

    @Override
    public List<User> searchUsersByUsername(String prefix) throws SQLException {
        List<User> res = new ArrayList<>();

        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("select * from ").append(TABLENAME).append(" where username like '");
        code.append(prefix).append("%';");

        ResultSet rs = statement.executeQuery(code.toString());

        return getUsers(connection, statement, rs);
    }

    @Override
    public void clearUsers() throws SQLException {
        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder code = new StringBuilder();

        statement.execute("USE " + DBNAME + ";\n");

        code.append("DELETE FROM users;");

        statement.executeUpdate(code.toString());

        statement.close();
        ConnectionPool.releaseConnection(connection);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> res = new ArrayList<>();

        Connection connection = ConnectionPool.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("select * from ").append(TABLENAME).append(";");

        ResultSet rs = statement.executeQuery(code.toString());

        return getUsers(connection, statement, rs);
    }

    private User getUser(Connection connection, Statement statement, StringBuilder code) throws SQLException {
        User res = null;
        ResultSet rs = statement.executeQuery(code.toString());
        while(rs.next()){
            res = new User(rs.getString("username"), rs.getString("hashedPassword"),
                    rs.getString("firstname"), rs.getString("lastname"),
                    rs.getString("email"));
        }


        statement.close();
        ConnectionPool.releaseConnection(connection);

        return res;
    }

    private List<User> getUsers(Connection connection, Statement statement, ResultSet rs) throws SQLException {
        List<User> res = new ArrayList<>();
        while(rs.next()){
            User user = new User(rs.getString("username"), rs.getString("hashedPassword"),
                    rs.getString("firstname"), rs.getString("lastname"),
                    rs.getString("email"));
            res.add(user);
        }

        statement.close();
        ConnectionPool.releaseConnection(connection);

        return res;
    }
}