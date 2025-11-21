/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.poe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_Lab
 */

public class MessageManagementTest {

    private MessageManagement manager;

    @BeforeEach
    void setUp() {
        manager = new MessageManagement();

        Message m1 = new Message("1", "+27834557896", "Did you get the cake?");
        Message m2 = new Message("2", "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message m3 = new Message("3", "+27834484567", "Yoohooo, I am at your gate.");
        Message m4 = new Message("4", "0838884567", "It is dinner time!");
        Message m5 = new Message("5", "+27838884567", "Ok, I am leaving without you.");

        manager.addMessage(m1);
        manager.addMessage(m2);
        manager.addMessage(m3);
        manager.addMessage(m4);
        manager.addMessage(m5);
    }

    @Test
    void testMessagesStoredCorrectly() {
        assertEquals(5, manager.sentMessage.size());
        assertTrue(manager.sentMessage.contains("Did you get the cake?"));
        assertTrue(manager.sentMessage.contains("It is dinner time!"));
    }

    @Test
    void testLongestMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        String longest = manager.sentMessage.stream()
                .max((a, b) -> a.length() - b.length())
                .orElse("");

        assertEquals(expected, longest);
    }

    @Test
    void testSearchByID() {
        int index = manager.uniqueMessageID.indexOf("4");
        assertNotEquals(-1, index);
        assertEquals("It is dinner time!", manager.sentMessage.get(index));
    }

    @Test
    void testSearchByRecipient() {
        String phone = "+27838884567";

        long count = manager.recipientPhone.stream()
                .filter(p -> p.equals(phone))
                .count();

        assertEquals(2, count); // Messages 2 and 5
    }

    @Test
    void testDeleteMessage() {
        // Take hash of message 2
        String hash = manager.hashID.get(1);

        int oldSize = manager.hashID.size();

        // Delete it manually 
        int index = manager.hashID.indexOf(hash);
        manager.deletedMessages.add("ID: " + manager.uniqueMessageID.get(index)
                + ", Phone: " + manager.recipientPhone.get(index)
                + ", Text: " + manager.sentMessage.get(index));

        manager.uniqueMessageID.remove(index);
        manager.recipientPhone.remove(index);
        manager.sentMessage.remove(index);
        manager.hashID.remove(index);

        assertEquals(oldSize - 1, manager.hashID.size());
        assertEquals(1, manager.deletedMessages.size());
        assertTrue(manager.deletedMessages.get(0).contains("Where are you? You are late!"));
    }

    @Test
    void testReportDataExists() {
        assertEquals(5, manager.uniqueMessageID.size());
        assertEquals(5, manager.recipientPhone.size());
        assertEquals(5, manager.sentMessage.size());
    }
}