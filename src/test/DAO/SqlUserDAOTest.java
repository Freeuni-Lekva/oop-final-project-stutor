package DAO;

import DAO.Interfaces.UserDAO;
import at.favre.lib.crypto.bcrypt.BCrypt;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import Model.User;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SqlUserDAOTest extends TestCase {
    private static UserDAO userDAO;
    private ConnectionPool connectionPool;

    @BeforeAll
    public void setUp() throws ClassNotFoundException, SQLException {
        userDAO = new SqlUserDAO();
        connectionPool = new ConnectionPool();
        userDAO.clearUsers();
    }

    @AfterEach
    public void clearUp() throws SQLException {
        userDAO.clearUsers();
    }

    public void testAdd() throws SQLException {
        User user = new User("SlowDancer", getHash("123"), "irakli",
                "khutsishvili", "ikhut21");

        assertTrue(userDAO.addUser(user));
        assertFalse(userDAO.addUser(user));
        userDAO.clearUsers();
    }

    public void testRemove() throws SQLException {
        User user = new User("SlowDancer", getHash("123"), "irakli",
                "khutsishvili", "ikhut21");

        assertTrue(userDAO.addUser(user));
        assertTrue(userDAO.removeUser(user.getUsername()));
        userDAO.clearUsers();
    }

    public void testGetUserByEmail() throws SQLException {
        User user = new User("SlowDancer", getHash("123"), "irakli",
                "khutsishvili", "ikhut21");

        assertTrue(userDAO.addUser(user));
        assertEquals(user, userDAO.getUserByEmail("ikhut21"));
        assertTrue(userDAO.removeUser(user.getUsername()));
        assertNull(userDAO.getUserByEmail("ikhut21"));
        userDAO.clearUsers();
    }

    public void testGetUserByUsername() throws SQLException {
        User user = new User("SlowDancer", getHash("123"), "irakli",
                "khutsishvili", "ikhut21");

        assertTrue(userDAO.addUser(user));
        assertEquals(user, userDAO.getUserByUsername("SlowDancer"));
        assertTrue(userDAO.removeUser(user.getUsername()));
        assertNull(userDAO.getUserByEmail("ikhut21"));
        userDAO.clearUsers();
    }

    public void testIsValid() throws SQLException {
        User user = new User("SlowDancer", getHash("123"), "irakli",
                "khutsishvili", "ikhut21");

        assertTrue(userDAO.addUser(user));
        assertTrue(userDAO.isValidUser("ikhut21", "123"));
        userDAO.clearUsers();
    }

    public void testSearchUserByUsername() throws SQLException {
        List<User> users = Arrays.asList(new User("SlowDancer", getHash("123"), "irakli",
                "khutsishvili", "ikhut21"),
                new User("Slow", getHash("123"), "irakli",
                        "khutsishvili", "ikhut"),
                new User("Dancer", getHash("123"), "irakli",
                        "khutsishvili", "21"),
                new User("AAA", getHash("123"), "irakli",
                        "khutsishvili", "ttt")
                );

        List<User> res = Arrays.asList(new User("SlowDancer", getHash("123"), "irakli",
                        "khutsishvili", "ikhut21"),
                new User("Slow", getHash("123"), "irakli",
                        "khutsishvili", "ikhut")
        );

        for (User user : users) {
            assertTrue(userDAO.addUser(user));
        }

        List<User> toCheck = userDAO.searchUsersByUsername("Slow");
        assertEquals(res.size(), toCheck.size());

        for(int i = 0; i < res.size(); i++){
            assertTrue(res.contains(toCheck.get(i)));
        }
        userDAO.clearUsers();
    }

    public void testGetAllUsers() throws SQLException {
        List<User> users = Arrays.asList(new User("SlowDancer", getHash("123"), "irakli",
                        "khutsishvili", "ikhut21"),
                new User("Slow", getHash("123"), "irakli",
                        "khutsishvili", "ikhut"),
                new User("Dancer", getHash("123"), "irakli",
                        "khutsishvili", "21"),
                new User("AAA", getHash("123"), "irakli",
                        "khutsishvili", "ttt")
        );
        List<User> res = Arrays.asList(new User("SlowDancer", getHash("123"), "irakli",
                        "khutsishvili", "ikhut21"),
                new User("Slow", getHash("123"), "irakli",
                        "khutsishvili", "ikhut"),
                new User("Dancer", getHash("123"), "irakli",
                        "khutsishvili", "21"),
                new User("AAA", getHash("123"), "irakli",
                        "khutsishvili", "ttt")
        );

        for (User user : users) {
            assertTrue(userDAO.addUser(user));
        }

        List<User> toCheck = userDAO.getAllUsers();
        assertEquals(res.size(), toCheck.size());

        for(int i = 0; i < res.size(); i++){
            assertTrue(res.contains(toCheck.get(i)));
        }
        userDAO.clearUsers();
    }

    private static String getHash(String password) {
        BCrypt.Hasher hasher = BCrypt.withDefaults();
        char[] chars = new char[password.length()];
        for (int i = 0; i < password.length(); i++) {
            chars[i] = password.charAt(i);
        }
        String hashedPassword = hasher.hashToString(10, chars);
        return hashedPassword;
    }
}