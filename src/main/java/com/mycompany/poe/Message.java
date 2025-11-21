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
import java.io.FileWriter;
import java.io.IOException;

public class Message {
    
    String messageID;
    String recipientCell;
    String messageText;
    String messageHash;

     Message(String id, String recipient, String text) {
        this.messageID = id == null ? "" : id;
        this.recipientCell = recipient == null ? "" : recipient;
        this.messageText = text == null ? "" : text;
    }

    // Validation 
    
     String validateMessage() {
        if (messageID.length() == 0) return "Message ID cannot be empty.";
        if (messageID.length() > 10) return "Message ID too long (max 10 characters).";
        if (!(recipientCell.startsWith("0") && recipientCell.length() == 10))
            return "Invalid recipient number. Must start with 0 and be 10 digits.";
        if (messageText.length() == 0) return "Message cannot be empty.";
        if (messageText.length() > 250) return "Message too long (max 250 characters).";
        return "OK";
    }

    // create a hash 
    
     String createMessageHash() {
      String last4 = recipientCell.substring(recipientCell.length() - 4);
       messageHash = messageID + last4;
        return messageHash;
    }

     String handleMessage() {
        String[] options = {"Send", "Store", "Disregard"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose what to do with this message:",
                "Message Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);

        if (choice == 0) {
            sendMessage();
            return "Message Sent";
        } else if (choice == 1) {
            boolean written = storeMessage();
            return written ? "Message Stored" : "Message Disregarded";
        } else {
            JOptionPane.showMessageDialog(null, "Message disregarded.");
            return "Message Disregarded";
        }
    }

    public void sendMessage() {
        
        JOptionPane.showMessageDialog(null, " Message sent successfully!");
    }


    // JSON stroing messages by ChatGPT
    
    public boolean storeMessage() {
        createMessageHash();
        String json = toJsonString();

        try (FileWriter fw = new FileWriter("messages.json", true)) {
            fw.write(json);
            fw.write(System.lineSeparator()); // keep each record on new line
            fw.flush();
            JOptionPane.showMessageDialog(null, "Message stored to JSON successfully!");
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving message: " + e.getMessage());
            return false;
        }
    }

    String toJsonString() {
        return "{" +
                "\"MessageID\":\"" + escapeJson(messageID) + "\"," +
                "\"MessageHash\":\"" + escapeJson(messageHash == null ? createMessageHash() : messageHash) + "\"," +
                "\"Recipient\":\"" + escapeJson(recipientCell) + "\"," +
                "\"Message\":\"" + escapeJson(messageText) + "\"" +
                "}";
    }

     String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

   
     String getId() {
        return messageID;
    }

     String getRecipient() {
        return recipientCell;
    }

     String getText() {
        return messageText;
        
    }// End of method 
    
}// End of class




