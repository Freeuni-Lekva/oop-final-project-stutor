package Model;

import Model.User;
import junit.framework.TestCase;

import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

public class UserTest extends TestCase {
    private List<User> users;
    private final static int NUM_USERS = 10;
    @BeforeAll
    public void setUp(){
        users = new ArrayList<>();
        for(Integer i = 0; i < NUM_USERS; i++){
            User user = new User(i.toString(), i.toString(), i.toString(), i.toString(), i.toString());
            users.add(user);
        }
    }

    public void testUser(){
        for(Integer i = 0; i < NUM_USERS; i++){
            User user = new User(i.toString(), i.toString(), i.toString(), i.toString(), i.toString());

            assertEquals(user.getUsername(), users.get(i).getUsername());
            assertEquals(user.getHashedPassword(), users.get(i).getHashedPassword());
            assertEquals(user.getFirstname(), users.get(i).getFirstname());
            assertEquals(user.getLastname(), users.get(i).getLastname());
            assertEquals(user.getEmail(), users.get(i).getEmail());

            assertTrue(user.equals(users.get(i)));
        }
    }
}
