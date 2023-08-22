package DAO;

import DAO.Interfaces.ChatDAO;
import DAO.Interfaces.UserDAO;
import Model.Message;
import Model.User;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SqlChatDAOTest extends TestCase {
    private static ChatDAO chatDAO;
    private static UserDAO userDAO;
    private ConnectionPool connectionPool;

    @BeforeAll
    public void setUp() throws ClassNotFoundException, SQLException {
        chatDAO = new SqlChatDAO();
        userDAO = new SqlUserDAO();
        connectionPool = new ConnectionPool();
        chatDAO.deleteChat();
        userDAO.clearUsers();
    }

    @AfterEach
    public void clearUp() throws SQLException {
        chatDAO.deleteChat();
        userDAO.clearUsers();
    }

    public void testSendMessage() throws SQLException {
        Message msg1 = new Message("akali", "kayn", "You are pretty cool");
        Message msg2 = new Message("kayn", "akali", "You As well");

        User user1 = new User("akali", "1", "2", "3", "4");
        User user2 = new User("kayn", "1", "2", "3", "5");

        assertTrue(userDAO.addUser(user1));
        assertTrue(userDAO.addUser(user2));

        assertTrue(chatDAO.sendMessage(msg1));
        assertTrue(chatDAO.sendMessage(msg2));
        chatDAO.deleteChat();
        userDAO.clearUsers();
    }

    public void testGetUsers() throws SQLException {
        Message msg1 = new Message("akali", "kayn", "You are pretty cool");
        Message msg2 = new Message("kayn", "akali", "You As well");
        Message msg3 = new Message("kayn", "Zed", "master zed");

        User user1 = new User("akali", "1", "2", "3", "4");
        User user2 = new User("kayn", "1", "2", "3", "5");
        User user3 = new User("Zed", "1", "2", "3", "6");

        assertTrue(userDAO.addUser(user1));
        assertTrue(userDAO.addUser(user2));
        assertTrue(userDAO.addUser(user3));

        assertTrue(chatDAO.sendMessage(msg1));
        assertTrue(chatDAO.sendMessage(msg2));
        assertTrue(chatDAO.sendMessage(msg3));

        List<String> users = chatDAO.getUsers("akali");
        List<String> res = Arrays.asList("kayn");
        assertEquals(res.size(), users.size());

        for(String user : users){
            assertTrue(res.contains(user));
        }

        users = chatDAO.getUsers("kayn");
        res = Arrays.asList("akali", "Zed");
        assertEquals(2, users.size());
        for(String user : users){
            assertTrue(res.contains(user));
        }
        chatDAO.deleteChat();
        userDAO.clearUsers();
    }

    public void testGetAndDeleteConversation() throws SQLException {
        Message msg1 = new Message("akali", "kayn", "You are pretty cool");
        Message msg2 = new Message("kayn", "akali", "You As well");
        Message msg3 = new Message("kayn", "Zed", "master zed");

        User user1 = new User("akali", "1", "2", "3", "4");
        User user2 = new User("kayn", "1", "2", "3", "5");
        User user3 = new User("Zed", "1", "2", "3", "6");

        assertTrue(userDAO.addUser(user1));
        assertTrue(userDAO.addUser(user2));
        assertTrue(userDAO.addUser(user3));

        assertTrue(chatDAO.sendMessage(msg1));
        assertTrue(chatDAO.sendMessage(msg2));
        assertTrue(chatDAO.sendMessage(msg3));

        List<Message> msgs = chatDAO.getConversation("akali", "kayn");
        List<Message> res = Arrays.asList(msg2, msg1);
        assertEquals(res.size(), msgs.size());

        for(int i = 0; i < res.size(); i++){
            assertEquals(res.get(i).getSender(), msgs.get(i).getSender());
            assertEquals(res.get(i).getReceiver(), msgs.get(i).getReceiver());
            assertEquals(res.get(i).getMessage(), msgs.get(i).getMessage());
        }

        chatDAO.deleteConversation("akali", "kayn");
        msgs = chatDAO.getConversation("akali", "kayn");
        assertEquals(0, msgs.size());
        chatDAO.deleteChat();
        userDAO.clearUsers();
    }
}