package DAO;

import DAO.Interfaces.UserDAO;
import Model.User;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlUserDAO implements UserDAO {
    private final static String USERNAME = "sqluser";
    private final static String PASSWORD = "password";
    private final static String DBNAME = "stutor_db";
    private final static String TABLENAME = "users";
    BasicDataSource dataSource;

    public SqlUserDAO() throws ClassNotFoundException {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + DBNAME);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        Class.forName("com.mysql.cj.jdbc.Driver");
    }


    @Override
    public boolean addUser(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("INSERT INTO " + TABLENAME + " (username, hashedPassword, firstname, lastname, email) VALUES (");

        code.append("'" + user.getUsername() + "', ");
        code.append("'" + user.getHashedPassword() + "', ");
        code.append("'" + user.getFirstname() + "', ");
        code.append("'" + user.getLastname() + "', ");
        code.append("'" + user.getEmail() + "');");

        int check = statement.executeUpdate(code.toString());

        statement.close();
        connection.close();

        return check == 1;

    }

    @Override
    public boolean removeUser(User user) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("DELETE FROM users WHERE ");
        code.append("username = '").append(user.getUsername()).append("';");

        int check = statement.executeUpdate(code.toString());

        statement.close();
        connection.close();

        return check == 1;
    }

    @Override
    public User getUserByEmail(String email) throws SQLException {
        User res = null;

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("select * from ").append(TABLENAME);
        code.append(" where email = '").append(email).append("';");

        ResultSet rs = statement.executeQuery(code.toString());
        while(rs.next()){
            res = new User(rs.getString("username"), rs.getString("hashedpassword"),
                    rs.getString("firstname"), rs.getString("lastname"),
                    rs.getString("email"));
            res.setUserId(rs.getInt("user_id"));
        }


        statement.close();
        connection.close();

        return res;
    }

    @Override
    public List<User> getUsersByUsername(String username) throws SQLException {
        List<User> res = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        statement.execute("USE " + DBNAME + ";\n");

        StringBuilder code = new StringBuilder();

        code.append("select * from ").append(TABLENAME);
        code.append(" where username like '%").append(username).append("%'");

        ResultSet rs = statement.executeQuery(code.toString());
        while(rs.next()){
            User tmp = new User(rs.getString("username"), rs.getString("hashedpassword"),
                    rs.getString("firstname"), rs.getString("lastname"),
                    rs.getString("email"));
            tmp.setUserId(rs.getInt("user_id"));
            res.add(tmp);
        }

        statement.close();
        connection.close();

        return res;
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
        Connection connection = dataSource.getConnection();
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
        connection.close();

        return updated == 1;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return getUsersByUsername("");
    }

    @Override
    public void clearUsers() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        StringBuilder code = new StringBuilder();

        statement.execute("USE " + DBNAME + ";\n");

        code.append("DELETE FROM ").append(TABLENAME).append(";");

        statement.executeUpdate(code.toString());

        statement.close();
        connection.close();
    }
}
