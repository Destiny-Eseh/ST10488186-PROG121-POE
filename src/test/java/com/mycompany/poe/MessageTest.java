/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_Lab
 */

public class MessageTest {

    String personalCell = "0815950322";
    String personalMsg = "Hi Destiny, hope your project is going well!";

    // VALIDATION TESTS // 

    @Test
    void testValidateMessageSuccess() {
        Message m = new Message("MSG01", personalCell, personalMsg);
        assertEquals("OK", m.validateMessage());
    }

    @Test
    void testEmptyMessageID() {
        Message m = new Message("", personalCell, personalMsg);
        assertEquals("Message ID cannot be empty.", m.validateMessage());
    }

    @Test
    void testLongMessageID() {
        Message m = new Message("ABCDEFGHIJK", personalCell, personalMsg);
        assertEquals("Message ID too long (max 10 characters).", m.validateMessage());
    }

    @Test
    void testInvalidRecipientNumber() {
        Message m = new Message("MSG01", "12345", personalMsg);
        assertEquals("Invalid recipient number. Must start with 0 and be 10 digits.", m.validateMessage());
    }

    @Test
    void testEmptyMessageText() {
        Message m = new Message("MSG01", personalCell, "");
        assertEquals("Message cannot be empty.", m.validateMessage());
    }

    @Test
    void testTooLongMessage() {
        String longText = new String(new char[251]).replace("\0", "A");
        Message m = new Message("MSG01", personalCell, longText);
        assertEquals("Message too long (max 250 characters).", m.validateMessage());
    }

    // HASH CREATION // 

    @Test
    public void testMessageHash() {
        Message m = new Message("MSG01", personalCell, personalMsg);

        // last 4 digits of "0815950322" = "0322"
        assertEquals("MSG010322", m.createMessageHash());
    }

    // GETTERS //

    @Test
    public void testGetters() {
        Message m = new Message("MSG77", personalCell, personalMsg);
        assertEquals("MSG77", m.getId());
        assertEquals(personalCell, m.getRecipient());
        assertEquals(personalMsg, m.getText());
    }

    // JSON ESCAPE FUNCTION // 

    @Test
    public void testEscapeJsonSpecialChars() {
        Message m = new Message("ID1", personalCell, personalMsg);
        String escaped = m.escapeJson("He said \"Hello\"");
        assertEquals("He said \\\"Hello\\\"", escaped);
    }

    @Test
    public void testJsonFormat() {
        Message m = new Message("MSG01", personalCell, "Testing JSON");
        String hash = m.createMessageHash();

        String json = "{" +
                "\"MessageID\":\"MSG01\"," +
                "\"MessageHash\":\"" + hash + "\"," +
                "\"Recipient\":\"" + personalCell + "\"," +
                "\"Message\":\"Testing JSON\"" +
                "}";

        assertEquals(json, m.toJsonString());
    }
}