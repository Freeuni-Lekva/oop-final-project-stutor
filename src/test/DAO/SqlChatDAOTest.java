package DAO;

import DAO.Interfaces.ChatDAO;
import Model.Message;
import junit.framework.TestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SqlChatDAOTest extends TestCase {
    private static ChatDAO chatDAO;
    private ConnectionPool connectionPool;

    @BeforeAll
    public void setUp() throws ClassNotFoundException, SQLException {
        chatDAO = new SqlChatDAO();
        connectionPool = new ConnectionPool();
        chatDAO.deleteChat();
    }

    @AfterEach
    public void clearUp() throws SQLException {
        chatDAO.deleteChat();
    }

    public void testSendMessage() throws SQLException {
        Message msg1 = new Message("akali", "kayn", "You are pretty cool");
        Message msg2 = new Message("kayn", "akali", "You As well");

        assertTrue(chatDAO.sendMessage(msg1));
        assertTrue(chatDAO.sendMessage(msg2));
    }

    public void testGetUsers() throws SQLException {
        Message msg1 = new Message("akali", "kayn", "You are pretty cool");
        Message msg2 = new Message("kayn", "akali", "You As well");
        Message msg3 = new Message("kayn", "Zed", "master zed");

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
    }

    public void testGetAndDeleteConversation() throws SQLException {
        Message msg1 = new Message("akali", "kayn", "You are pretty cool");
        Message msg2 = new Message("kayn", "akali", "You As well");
        Message msg3 = new Message("kayn", "Zed", "master zed");

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
    }
}