package Model;

import junit.framework.TestCase;

public class MessageTest extends TestCase {

    public void testConstructor() {
        Message message1 = new Message("1", "2", "Hello, world!");
        assertEquals("1", message1.getSender());
        assertEquals("2", message1.getReceiver());
        assertEquals("Hello, world!", message1.getMessage());

        Message message2 = new Message("3", "4", "");
        assertEquals("3", message2.getSender());
        assertEquals("4", message2.getReceiver());
        assertEquals("", message2.getMessage());

        String longMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla facilisi.";
        Message message3 = new Message("5", "6", longMessage);
        assertEquals("5", message3.getSender());
        assertEquals("6", message3.getReceiver());
        assertEquals(longMessage, message3.getMessage());

        String specialChars = "This message contains @#$% special characters! *&^";
        Message message4 = new Message("7", "8", specialChars);
        assertEquals("7", message4.getSender());
        assertEquals("8", message4.getReceiver());
        assertEquals(specialChars, message4.getMessage());
    }

    public void testSetters() {
        Message message1 = new Message("1", "2", "Hello, world!");
        message1.setMessage_id(10);
        assertEquals(10, message1.getMessage_id());

        Message message2 = new Message("3", "4", "Test message");
        message2.setMessage_id(0);
        assertEquals(0, message2.getMessage_id());

        Message message3 = new Message("5", "6", "Another message");
        message3.setMessage_id(-5);
        assertEquals(-5, message3.getMessage_id());

        Message message4 = new Message("7", "8", "Yet another message");
        message4.setMessage_id(1000000);
        assertEquals(1000000, message4.getMessage_id());
    }

    public void testEquals(){
        Message msg1 = new Message("Ruska", "irakli", "irakli, irakli");
        Message msg2 = new Message("irakli", "Ruska", "Ruska, Ruska");

        msg1.setMessage_id(1);
        msg2.setMessage_id(1);

        assertEquals(msg1, msg2);

        msg2.setMessage_id(2);

        assertFalse(msg1.equals(msg2));
    }

}