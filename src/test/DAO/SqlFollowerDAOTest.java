package DAO;

import DAO.Interfaces.FollowerDAO;
import DAO.Interfaces.UserDAO;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SqlFollowerDAOTest {
    private FollowerDAO followerDAO;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() throws ClassNotFoundException, SQLException {
        followerDAO = new SqlFollowerDAO();
        userDAO = new SqlUserDAO();
        userDAO.clearUsers();
    }

    @AfterEach
    public void cleanUp() throws SQLException {
        userDAO.clearUsers();
    }

    @Test
    void addFollower() throws SQLException {
        User newUser = new User("testUser1", "hashedPass", "John", "Doe", "johndoe1@example.com");
        User newUser1 = new User("testUser2", "hashedPass", "John", "Doe", "johndoe2@example.com");
        User newUser2 = new User("testUser3", "hashedPass", "John", "Doe", "johndoe3@example.com");

        userDAO.addUser(newUser);
        userDAO.addUser(newUser1);
        userDAO.addUser(newUser2);

        boolean check = followerDAO.addFollower(userDAO.getUserByEmail("johndoe1@example.com").getUserId(), userDAO.getUserByEmail("johndoe2@example.com").getUserId());
        assertEquals(check, true);
        check = followerDAO.addFollower(userDAO.getUserByEmail("johndoe1@example.com").getUserId(), userDAO.getUserByEmail("johndoe3@example.com").getUserId());
        assertEquals(check, true);

    }

    @Test
    void removeFollower() throws SQLException {
        User newUser = new User("testUser1", "hashedPass", "John", "Doe", "johndoe1@example.com");
        User newUser1 = new User("testUser2", "hashedPass", "John", "Doe", "johndoe2@example.com");
        User newUser2 = new User("testUser3", "hashedPass", "John", "Doe", "johndoe3@example.com");

        userDAO.addUser(newUser);
        userDAO.addUser(newUser1);
        userDAO.addUser(newUser2);

        followerDAO.addFollower(userDAO.getUserByEmail("johndoe1@example.com").getUserId(), userDAO.getUserByEmail("johndoe2@example.com").getUserId());
        followerDAO.addFollower(userDAO.getUserByEmail("johndoe1@example.com").getUserId(), userDAO.getUserByEmail("johndoe3@example.com").getUserId());

        boolean check = followerDAO.removeFollower(userDAO.getUserByEmail("johndoe1@example.com").getUserId(), userDAO.getUserByEmail("johndoe2@example.com").getUserId());
        assertEquals(check, true);
        check = followerDAO.removeFollower(userDAO.getUserByEmail("johndoe1@example.com").getUserId(), userDAO.getUserByEmail("johndoe3@example.com").getUserId());
        assertEquals(check, true);
        check = followerDAO.removeFollower(userDAO.getUserByEmail("johndoe1@example.com").getUserId(), userDAO.getUserByEmail("johndoe2@example.com").getUserId());
        assertEquals(check, false);

    }

    @Test
    void getFollower() throws SQLException {
        User newUser = new User("testUser1", "hashedPass", "John", "Doe", "johndoe1@example.com");
        User newUser1 = new User("testUser2", "hashedPass", "John", "Doe", "johndoe2@example.com");
        User newUser2 = new User("testUser3", "hashedPass", "John", "Doe", "johndoe3@example.com");

        userDAO.addUser(newUser);
        userDAO.addUser(newUser1);
        userDAO.addUser(newUser2);

        followerDAO.addFollower(userDAO.getUserByEmail("johndoe1@example.com").getUserId(), userDAO.getUserByEmail("johndoe2@example.com").getUserId());
        followerDAO.addFollower(userDAO.getUserByEmail("johndoe1@example.com").getUserId(), userDAO.getUserByEmail("johndoe3@example.com").getUserId());

        List<User> list = followerDAO.getFollowers(userDAO.getUserByEmail("johndoe1@example.com").getUserId());

        assertEquals(list.size(), 2);

        assertEquals(list.get(0).getUsername(), "testUser2");
        assertEquals(list.get(1).getUsername(), "testUser3");

        followerDAO.addFollower(userDAO.getUserByEmail("johndoe2@example.com").getUserId(), userDAO.getUserByEmail("johndoe1@example.com").getUserId());

        list = followerDAO.getFollowers(userDAO.getUserByEmail("johndoe2@example.com").getUserId());

        assertEquals(list.size(), 1);

        assertEquals(list.get(0).getUsername(), "testUser1");
    }

}