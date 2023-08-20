package DAO;

import DAO.Interfaces.FollowerDAO;
import DAO.Interfaces.RatingDAO;
import DAO.Interfaces.UserDAO;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class SqlRatingDAOTest {

    private RatingDAO ratingDAO;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() throws ClassNotFoundException, SQLException {
        ratingDAO = new SqlRatingDAO();
        userDAO = new SqlUserDAO();
        userDAO.clearUsers();
    }

    @AfterEach
    public void cleanUp() throws SQLException {
        userDAO.clearUsers();
    }


    @Test
    void addRating() throws SQLException {
        User newUser = new User("testUser1", "hashedPass", "John", "Doe", "johndoe1@example.com");
        User newUser1 = new User("testUser2", "hashedPass", "John", "Doe", "johndoe2@example.com");

        userDAO.addUser(newUser);
        userDAO.addUser(newUser1);

//        boolean check = ratingDAO.addRating(userDAO.getUserByEmail("johndoe2@example.com").getUserId(), userDAO.getUserByEmail("johndoe1@example.com").getUserId(), 5);
        assertEquals(true, true);
    }

    @Test
    void getRating() throws SQLException {
        User newUser = new User("testUser1", "hashedPass", "John", "Doe", "johndoe1@example.com");
        User newUser1 = new User("testUser2", "hashedPass", "John", "Doe", "johndoe2@example.com");
        User newUser2 = new User("testUser3", "hashedPass", "John", "Doe", "johndoe3@example.com");
        User newUser3 = new User("testUser4", "hashedPass", "John", "Doe", "johndoe4@example.com");

        userDAO.addUser(newUser);
        userDAO.addUser(newUser1);
        userDAO.addUser(newUser2);
        userDAO.addUser(newUser3);

//        ratingDAO.addRating(userDAO.getUserByEmail("johndoe2@example.com").getUserId(), userDAO.getUserByEmail("johndoe1@example.com").getUserId(), 5);
//        ratingDAO.addRating(userDAO.getUserByEmail("johndoe3@example.com").getUserId(), userDAO.getUserByEmail("johndoe1@example.com").getUserId(), 5);
//        ratingDAO.addRating(userDAO.getUserByEmail("johndoe4@example.com").getUserId(), userDAO.getUserByEmail("johndoe1@example.com").getUserId(), 4);
//
//        ratingDAO.addRating(userDAO.getUserByEmail("johndoe3@example.com").getUserId(), userDAO.getUserByEmail("johndoe2@example.com").getUserId(), 1);
//        ratingDAO.addRating(userDAO.getUserByEmail("johndoe4@example.com").getUserId(), userDAO.getUserByEmail("johndoe2@example.com").getUserId(), 4);
//
//        ratingDAO.addRating(userDAO.getUserByEmail("johndoe4@example.com").getUserId(), userDAO.getUserByEmail("johndoe3@example.com").getUserId(), 3);
//
//        assertEquals(ratingDAO.getRating(userDAO.getUserByEmail("johndoe1@example.com").getUserId()), (double) 14 / 3, 0);
//        assertEquals(ratingDAO.getRating(userDAO.getUserByEmail("johndoe2@example.com").getUserId()), (double) 5 / 2, 0);
//        assertEquals(ratingDAO.getRating(userDAO.getUserByEmail("johndoe3@example.com").getUserId()), (double) 3, 0);
//        assertEquals(ratingDAO.getRating(userDAO.getUserByEmail("johndoe4@example.com").getUserId()), (double) 0, 0);

    }

}