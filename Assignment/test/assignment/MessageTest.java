package assignment;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("1234567890", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 260; i++) {
            sb.append("a");
        }
        Message msg = new Message("1234567890", 0, "+27718693002", sb.toString());
        assertTrue(msg.checkMessageLength().contains("Message exceeds 250 characters"));
    }

    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message("1234567890", 0, "+27718693002", "Hello");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message("1234567890", 0, "08575975889", "Hello");
        assertTrue(msg.checkRecipientCell().contains("incorrectly formatted"));
    }

    @Test
    public void testMessageHash() {
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi tonight");
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    @Test
    public void testMessageIDValidation() {
        Message msg = new Message("1234567890", 0, "+27718693002", "Test");
        assertTrue(msg.checkMessageID());
    }
}