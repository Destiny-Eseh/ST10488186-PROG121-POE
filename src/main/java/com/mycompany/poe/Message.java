/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe;

/**
 *
 * @author RC_Student_Lab
 */

import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Message {
    String messageID;
    String recipientCell;
    String messageText;
    String messageHash;
    static int totalMessages = 0;
    static ArrayList<String> sentMessages = new ArrayList<>();

        Message(String id, String recipient, String text) {
        this.messageID = id;
        this.recipientCell = recipient;
        this.messageText = text;
    }

        Boolean checkMessageID() {
        return messageID.length() <= 10;
    }

        int checkRecipientCell() {
        if (recipientCell.length() <= 10 && recipientCell.startsWith("0")) {
            return 1;
        } else {
            return 0;
        }
    }

    String createMessageHash() {
        messageHash = Integer.toString(messageText.hashCode());
        return messageHash;
    }

        String SentMessage() {
        String[] options = {"Send", "Store", "Disregard"};
        int choice = JOptionPane.showOptionDialog(null,
                "Choose what to do with the message:",
                "Message Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            totalMessages++;
            sentMessages.add(messageText);
            JOptionPane.showMessageDialog(null, printMessages());
            return "Message Sent";
        } else if (choice == 1) {
            storeMessage();
            return "Message Stored";
        } else {
            return "Message Disregarded";
        }
    }

        String printMessages() {
        StringBuilder sb = new StringBuilder();
        sb.append("All Sent Messages:\n");
        for (String msg : sentMessages) {
            sb.append(msg).append("\n");
        }
        sb.append("\nTotal Messages Sent: ").append(totalMessages);
        return sb.toString();
    }

        int returnTotalMessages() {
        return totalMessages;
    }

       void storeMessage() {
        JSONObject obj = new JSONObject();
        obj.put("MessageID", messageID);
        obj.put("MessageHash", createMessageHash());
        obj.put("Recipient", recipientCell);
        obj.put("Message", messageText);

        JSONArray arr = new JSONArray();
        arr.put(obj);

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(arr.toString());
            file.flush();
            JOptionPane.showMessageDialog(null, "Message stored in JSON file successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error storing message: " + e.getMessage());
        }
    }

        String getMessageDetails() {
        return "MessageID: " + messageID +
                "\nMessageHash: " + createMessageHash() +
                "\nRecipient: " + recipientCell +
                "\nMessage: " + messageText;
        
    }// end of main method 
        
}// end of class
