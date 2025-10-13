/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_Lab
 */
public class MessageTest {

    Message message;

    @BeforeEach
    void setUp() {
        message = new Message("D001", "0712345678", "Hey Destiny, just checking in!");
    }

    @Test
    void testValidMessageID() {
        assertTrue(message.checkMessageID());
    }

    @Test
    void testInvalidMessageID() {
        Message m = new Message("DESTINY123456", "0712345678", "Long ID test");
        assertFalse(m.checkMessageID());
    }

    @Test
    void testValidRecipient() {
        assertEquals(1, message.checkRecipientCell());
    }

    @Test
    void testInvalidRecipientShort() {
        Message m = new Message("D002", "0712", "Short number test");
        assertEquals(0, m.checkRecipientCell());
    }

    @Test
    void testInvalidRecipientNoZeroStart() {
        Message m = new Message("D003", "8123456789", "Missing zero test");
        assertEquals(0, m.checkRecipientCell());
    }

    @Test
    void testSendMessage() {
        String result = message.SentMessage();
        assertNotNull(result);
    }

    @Test
    void testMessageDetails() {
        String details = message.getMessageDetails();
        assertTrue(details.contains("D001"));
        assertTrue(details.contains("0712345678"));
    }
}
