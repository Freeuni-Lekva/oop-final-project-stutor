package DAO;

import DAO.Interfaces.ChatDAO;
import Model.Message;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class SqlChatDAOTest extends TestCase {
    private ChatDAO chat;
    private List<String> msgs;
    @BeforeAll
    public void setUp() throws ClassNotFoundException, SQLException {
        chat = new SqlChatDAO();
        msgs = Arrays.asList("Naruto Magaria", "namdvilad", "kidev ici ra aris magari?",
                "ra?", "bleach", "mogewona rukias bankai?", "sami");
        chat.deleteChat();
    }

    public void testConversation() throws SQLException {
        Message msg1 = new Message(1, 2, msgs.get(0));
        chat.sendMessage(msg1);
        Message msg2 = new Message(2, 1, msgs.get(1));
        chat.sendMessage(msg2);
        Message msg3 = new Message(1, 2, msgs.get(2));
        chat.sendMessage(msg3);
        Message msg4 = new Message(2, 1, msgs.get(3));
        chat.sendMessage(msg4);
        Message msg5 = new Message(1, 2, msgs.get(4));
        chat.sendMessage(msg5);
        Message msg6 = new Message(2, 1, msgs.get(5));
        chat.sendMessage(msg6);
        Message msg7 = new Message(1, 3, msgs.get(6));
        chat.sendMessage(msg7);
        List<Message> conv = chat.getConversation(1, 2);
        for(int i = 0; i <= conv.size() - 1; i++){
            assertEquals(msgs.get(msgs.size() - 2 - i), conv.get(i).getMessage());
        }
        chat.deleteConversation(2, 1);
        conv = chat.getConversation(1, 2);
        assertEquals(0, conv.size());
    }

    public void testGetUsers() throws SQLException {
        Message msg1 = new Message(1, 2, msgs.get(0));
        chat.sendMessage(msg1);
        Message msg2 = new Message(2, 3, msgs.get(1));
        chat.sendMessage(msg2);
        Message msg3 = new Message(3, 1, msgs.get(2));
        chat.sendMessage(msg3);
        Message msg4 = new Message(1, 9, msgs.get(3));
        chat.sendMessage(msg4);
        Message msg5 = new Message(9, 1, msgs.get(4));
        chat.sendMessage(msg5);
        Message msg6 = new Message(3, 2, msgs.get(5));
        chat.sendMessage(msg6);
        Message msg7 = new Message(1, 3, msgs.get(6));
        chat.sendMessage(msg7);
        List<Integer> users = chat.getUsers(1);
        assertEquals((Integer) 3, users.get(0));
        assertEquals((Integer) 9, users.get(1));
        assertEquals((Integer) 2, users.get(2));
    }
}