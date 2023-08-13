package Model;

import Model.User;
import junit.framework.TestCase;

import org.junit.jupiter.api.BeforeAll;

public class UserTest extends TestCase {
    private User user;
    @BeforeAll
    public void setUp(){
        user = new User("WomanSlayer123",  "123", "chumber", "kukava", "akameGaKill@gmail.com");
    }

    public void testUser(){
        assertEquals("WomanSlayer123", user.getUsername());
        assertEquals("akameGaKill@gmail.com", user.getEmail());
        assertEquals("123", user.getHashedPassword());
        assertEquals("chumber", user.getFirstname());
        assertEquals("kukava", user.getLastname());
        user.setUserId(1);
        assertEquals(1, user.getUserId());
        User temp = new User("1", "2", "3", "4", "akameGaKill@gmail.com");
        assertTrue(temp.equals(user));
    }
}
