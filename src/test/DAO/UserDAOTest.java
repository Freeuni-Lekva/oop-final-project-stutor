package DAO;

import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import DAO.Interfaces.UserDAO;
import Model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDAOTest extends TestCase {

    private UserDAO userDAO;

    @BeforeEach
    public void setUp() throws ClassNotFoundException, SQLException {
        userDAO = new SqlUserDAO();
        userDAO.clearUsers();
    }

    @AfterEach
    public void cleanUp() throws SQLException {
        userDAO.clearUsers();
    }

    public void testAddUser() throws SQLException {
        User newUser = new User("testUser", "hashedPass", "John", "Doe", "johndoe@example.com");
        boolean check = userDAO.addUser(newUser);
        assertTrue(check);
    }

    public void testRemoveUser() throws SQLException {
        User newUser = new User("testUser1", "hashedPass", "John", "Doe", "johndoe@example.com");
        userDAO.addUser(newUser);
        boolean check = userDAO.removeUser(newUser);
        assertTrue(check);
    }

    public void testGetUserByEmail() throws SQLException {
        User newUser = new User("testUser2", "hashedPass", "John", "Doe", "johndoe@example.com");
        userDAO.addUser(newUser);
        User searched = userDAO.getUserByEmail("johndoe@example.com");
        assertTrue(newUser.equals(searched));
    }

    public void testGetUsersByUsername() throws SQLException {
        User newUser = new User("testUser3", "hashedPass", "John", "Doe", "johndoe@example.com");
        userDAO.addUser(newUser);
        User searched =  userDAO.getUsersByUsername("testUser3").get(0);
        assertTrue(searched.equals(newUser));
    }

    public void testGetAllUsers() throws SQLException {
        User newUser = new User("testUser3", "hashedPass", "John", "Doe", "johndoe@example.com");
        User newUser1 = new User("testUser", "hashedPass", "John", "Doe", "johndoe@example.com");
        User newUser2 = new User("3testUser", "hashedPass", "John", "Doe", "johndoe@example.com");
        userDAO.addUser(newUser);
        userDAO.addUser(newUser1);
        userDAO.addUser(newUser2);
        List<User> res =  userDAO.getAllUsers();
        assertEquals(3, res.size());
    }

    public void testIsValidUserAndSetPassword() throws SQLException {
        User newUser = new User("testUser", "hashedPass", "John", "Doe", "johndoe@example.com");
        userDAO.addUser(newUser);
        userDAO.setPassword("johndoe@example.com", "hashedPass");
        assertTrue(userDAO.isValidUser(newUser.getEmail(), newUser.getHashedPassword()));
    }
}
