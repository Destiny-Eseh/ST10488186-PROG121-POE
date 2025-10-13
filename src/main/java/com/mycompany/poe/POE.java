/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poe;

/**
 *
 * @author RC_Student_Lab
 */

import javax.swing.JOptionPane;

public class POE {
    public static void main(String[] args) {

        // User Registration Part 1
        
        UserRegistration reg = new UserRegistration();

        String u = JOptionPane.showInputDialog("Enter username: ");
        String p = JOptionPane.showInputDialog("Enter password: ");
        String ph = JOptionPane.showInputDialog("Enter phone number: ");

        String regMsg = reg.registerUser(u, p, ph);
        JOptionPane.showMessageDialog(null, regMsg);

        if (!regMsg.equals("User registered successfully!")) {
            return;
        }

        String lu = JOptionPane.showInputDialog("Enter username to login:");
        String lp = JOptionPane.showInputDialog("Enter password to login:");

        if (!reg.loginUser(lu, lp)) {
            JOptionPane.showMessageDialog(null, "Login failed. Incorrect username or password.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Login successful!\nWelcome " + lu + "!");

        // User Messaging Part 2 // 
        
        int totalMessages = 0;
        boolean keepSending = true;

        while (keepSending) {
            String messageID = JOptionPane.showInputDialog("Enter Message ID (max 10 characters):");
            String recipient = JOptionPane.showInputDialog("Enter Recipient Cell (starts with 0, max 10 digits):");
            String messageText = JOptionPane.showInputDialog("Enter your message (max 250 characters):");

            if (messageText.length() > 250) {
                JOptionPane.showMessageDialog(null, "Message too long! Please keep under 250 characters.");
                continue;
            }

            Message message = new Message(messageID, recipient, messageText);

            if (!message.checkMessageID()) {
                JOptionPane.showMessageDialog(null, "Message ID is too long!");
                continue;
            }

            if (message.checkRecipientCell() == 0) {
                JOptionPane.showMessageDialog(null, "Recipient number invalid!");
                continue;
            }

            String result = message.SentMessage();
            if (result.equals("Message Sent")) {
                totalMessages++;
                JOptionPane.showMessageDialog(null, "Message sent successfully!\n\n" + message.getMessageDetails());
            }

            int choice = JOptionPane.showConfirmDialog(null,
                    "Do you want to send another message?", "Continue", JOptionPane.YES_NO_OPTION);
            if (choice != JOptionPane.YES_OPTION) {
                keepSending = false;
            }
        }

        JOptionPane.showMessageDialog(null, "You sent a total of " + totalMessages + " message(s).");
        
    }// end of main method
    
}// end of class
