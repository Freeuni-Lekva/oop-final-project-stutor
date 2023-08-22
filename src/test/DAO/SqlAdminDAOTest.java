package DAO;

import DAO.Interfaces.AdminDAO;
import DAO.Interfaces.UserDAO;
import Model.User;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SqlAdminDAOTest extends TestCase {
    private static AdminDAO adminDAO;
    private static UserDAO userDAO;
    private ConnectionPool connectionPool;

    @BeforeAll
    public void setUp() throws ClassNotFoundException, SQLException {
        adminDAO = new SqlAdminDAO();
        userDAO = new SqlUserDAO();
        connectionPool = new ConnectionPool();
        adminDAO.clearAdmins();
        userDAO.clearUsers();
    }

    @AfterEach
    public void clearUp() throws SQLException {
        adminDAO.clearAdmins();
        userDAO.clearUsers();
    }

    public void testAddAdmin() throws SQLException {
        User user1 = new User("data", "1", "data",
                "shanidze", "dshan");
        User user2 = new User("ruska", "1", "ruska",
                "keldishvili", "rkeld");

        assertTrue(userDAO.addUser(user1));
        assertTrue(userDAO.addUser(user2));
        assertTrue(adminDAO.addAdmin(user1.getUsername()));
        assertTrue(adminDAO.addAdmin(user2.getUsername()));
        assertFalse(adminDAO.addAdmin(user1.getUsername()));

        adminDAO.clearAdmins();
        userDAO.clearUsers();
    }

    public void testIsAdmin() throws SQLException {
        User user1 = new User("data", "1", "data",
                "shanidze", "dshan");
        User user2 = new User("ruska", "1", "ruska",
                "keldishvili", "rkeld");

        assertTrue(userDAO.addUser(user1));
        assertTrue(userDAO.addUser(user2));

        assertFalse(adminDAO.isAdmin(user1.getUsername()));
        assertFalse(adminDAO.isAdmin(user2.getUsername()));

        assertTrue(adminDAO.addAdmin(user1.getUsername()));
        assertTrue(adminDAO.isAdmin(user1.getUsername()));
        adminDAO.clearAdmins();
        userDAO.clearUsers();
    }

    public void testGetAllAdmins() throws SQLException {
        User user1 = new User("data", "1", "data",
                "shanidze", "dshan");
        User user2 = new User("ruska", "1", "ruska",
                "keldishvili", "rkeld");

        assertTrue(userDAO.addUser(user1));
        assertTrue(userDAO.addUser(user2));
        assertTrue(adminDAO.addAdmin(user1.getUsername()));
        assertTrue(adminDAO.addAdmin(user2.getUsername()));

        List<String> admins = adminDAO.getAllAdmins();
        List<String> res = Arrays.asList("data", "ruska");
        assertEquals(res.size(), admins.size());

        for(String val : admins){
            assertTrue(res.contains(val));
        }
        adminDAO.clearAdmins();
        userDAO.clearUsers();
    }

    public void testRemoveAdmin() throws SQLException {
        User user1 = new User("data", "1", "data",
                "shanidze", "dshan");
        User user2 = new User("ruska", "1", "ruska",
                "keldishvili", "rkeld");

        assertTrue(userDAO.addUser(user1));
        assertTrue(userDAO.addUser(user2));
        assertTrue(adminDAO.addAdmin(user1.getUsername()));
        assertTrue(adminDAO.addAdmin(user2.getUsername()));

        List<String> admins = adminDAO.getAllAdmins();
        assertEquals(2, admins.size());

        assertTrue(adminDAO.removeAdmin("data"));
        admins = adminDAO.getAllAdmins();
        assertEquals(1, admins.size());

        assertEquals("ruska", admins.get(0));
        adminDAO.clearAdmins();
        userDAO.clearUsers();
    }
}