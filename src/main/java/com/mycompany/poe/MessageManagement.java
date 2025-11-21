package com.mycompany.poe;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_Lab
 */
import javax.swing.*;
import java.util.ArrayList;

public class MessageManagement {

    ArrayList<String> uniqueMessageID = new ArrayList<>();
    ArrayList<String> recipientPhone = new ArrayList<>();
    ArrayList<String> sentMessage = new ArrayList<>();
    ArrayList<String> hashID = new ArrayList<>();
    ArrayList<String> deletedMessages = new ArrayList<>();

    void addMessage(Message msg) {
        if (msg == null) return;

        String id = msg.getId();
        String recip = msg.getRecipient();
        String text = msg.getText();

        if (id == null) id = "";
        if (recip == null) recip = "";
        if (text == null) text = "";

        String last4 = recip.length() >= 4 ? recip.substring(recip.length() - 4) : recip;

        uniqueMessageID.add(id);
        recipientPhone.add(recip);
        sentMessage.add(text);

        String hash = id + last4 + Integer.toHexString(text.hashCode());
        hashID.add(hash);
    }

    void messageManagement() {
        boolean running = true;

        while (running) {
            String[] options = {"Search by ID", "Search by Recipient", "Delete Message", "Longest Message", "Show Report", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Message Management", "Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (choice) {
                case 0 -> searchByID();
                case 1 -> searchByRecipient();
                case 2 -> deleteMessage();
                case 3 -> showLongestMessage();
                case 4 -> showReport();
                default -> running = false;
            }
        }
    }

    // Search By ID
    
   void searchByID() {
        String id = JOptionPane.showInputDialog("Enter Message ID:");
        if (id == null) return;

        int index = uniqueMessageID.indexOf(id);
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Message not found.");
            return;
        }

        JOptionPane.showMessageDialog(null,
                "Message Found:\nRecipient: " + recipientPhone.get(index) +
                        "\nText: " + sentMessage.get(index) +
                        "\nHash: " + hashID.get(index));
    }

    // Search by Recipient 
    
    void searchByRecipient() {
        String phone = JOptionPane.showInputDialog("Enter recipient phone number:");
        if (phone == null) return;

        StringBuilder found = new StringBuilder("Messages for " + phone + ":\n\n");
        boolean anyFound = false;

        for (int i = 0; i < recipientPhone.size(); i++) {
            if (recipientPhone.get(i).equals(phone)) {
                anyFound = true;
                found.append("ID: ").append(uniqueMessageID.get(i)).append("\n");
                found.append("Message: ").append(sentMessage.get(i)).append("\n");
                found.append("Hash: ").append(hashID.get(i)).append("\n\n");
            }
        }

        if (!anyFound)
            JOptionPane.showMessageDialog(null, "No messages for this recipient.");
        else
            JOptionPane.showMessageDialog(null, found.toString());
    }

       // Delete a Message 
    
    void deleteMessage() {
        String inputHash = JOptionPane.showInputDialog("Enter the hash ID of the message to delete:");
        if (inputHash == null) return;

        int index = hashID.indexOf(inputHash);
        if (index == -1) {
            JOptionPane.showMessageDialog(null, "Message not found.");
            return;
        }

                deletedMessages.add("ID: " + uniqueMessageID.get(index) +
                ", Phone: " + recipientPhone.get(index) +
                ", Text: " + sentMessage.get(index));

        uniqueMessageID.remove(index);
        recipientPhone.remove(index);
        sentMessage.remove(index);
        hashID.remove(index);

        JOptionPane.showMessageDialog(null, "Message deleted successfully.");
    }

   // Display Longest Message 
    
    void showLongestMessage() {
        if (sentMessage.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages to check.");
            return;
        }

        String longest = sentMessage.get(0);
        for (String msg : sentMessage) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }

        JOptionPane.showMessageDialog(null, "Longest message:\n" + longest);
    }

    // Displays all the messages stored and Sent. 
    
    void showReport() {
        StringBuilder report = new StringBuilder("MESSAGE REPORT\n\n");

        for (int i = 0; i < uniqueMessageID.size(); i++) {
            report.append("ID: ").append(uniqueMessageID.get(i)).append("\n");
            report.append("Recipient: ").append(recipientPhone.get(i)).append("\n");
            report.append("Message: ").append(sentMessage.get(i)).append("\n");
            report.append("Hash: ").append(hashID.get(i)).append("\n\n");
        }

        report.append("\nDeleted Messages:\n");
        for (String d : deletedMessages) {
            report.append(d).append("\n");
        }

        JOptionPane.showMessageDialog(null, report.toString());
        
    }// End of main method 
    
}// End of class

