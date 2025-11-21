/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poe;

/**
 *
 * @author RC_Student_Lab
 */

import javax.swing.*;
import java.util.ArrayList;

public class POE {
    public static void main(String[] args) {

        // PART 3: MessageManagement link
        
        MessageManagement manager = new MessageManagement();

        UserRegistration reg = new UserRegistration();
        boolean registered = false;
        String username = "";
        String password = "";
        String phone = "";

        // PART 1: User Registration

        while (!registered) {
            username = JOptionPane.showInputDialog("Enter username (must contain '_' and ≤5 chars before it):");
            password = JOptionPane.showInputDialog("Enter password (8+ chars, 1 capital, 1 number, 1 special char):");
            phone = JOptionPane.showInputDialog("Enter phone number (+27XXXXXXXXX):");

            String result = reg.registerUser(username, password, phone);
            if (result.equals("User registered successfully!")) {
                registered = true;
            } else {
                int retry = JOptionPane.showConfirmDialog(null, "Would you like to try again?", "Retry Registration", JOptionPane.YES_NO_OPTION);
                if (retry != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Exiting registration.");
                    return;
                }
            }
        }

        // Login Section

        boolean loggedIn = false;
        while (!loggedIn) {
            String lu = JOptionPane.showInputDialog("Enter username to login:");
            String lp = JOptionPane.showInputDialog("Enter password to login:");
            loggedIn = reg.loginUser(lu, lp);
            if (!loggedIn) {
                int retry = JOptionPane.showConfirmDialog(null, "Try again?", "Login Failed", JOptionPane.YES_NO_OPTION);
                if (retry != JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Exiting program.");
                    return;
                }
            }
        }

        // PART 2: Send Messages

        ArrayList<Message> messages = new ArrayList<>();
        boolean loggedInMenu = true;

        while (loggedInMenu) {
            String[] mainOptions = {"Send Messages", "Manage Messages", "Logout"};
            int menuChoice = JOptionPane.showOptionDialog(null,
                    "Choose an option:",
                    "Main Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    mainOptions,
                    mainOptions[0]);

            switch (menuChoice) {
                case 0 -> { // Send Messages
                    String input = JOptionPane.showInputDialog("How many messages would you like to send?");
                    if (input == null) break;
                    int count;
                    try {
                        count = Integer.parseInt(input.trim());
                        if (count <= 0) {
                            JOptionPane.showMessageDialog(null, "Please enter a positive number.");
                            break;
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid number entered.");
                        break;
                    }

                    for (int i = 0; i < count; i++) {
                        String id = JOptionPane.showInputDialog("Enter Message ID (max 10 chars):");
                        if (id == null) break;

                        String recipient = JOptionPane.showInputDialog("Enter recipient cell (starts with 0, 10 digits):");
                        if (recipient == null) break;

                        String text = JOptionPane.showInputDialog("Enter your message (max 250 chars):");
                        if (text == null) break;

                        Message msg = new Message(id.trim(), recipient.trim(), text);
                        String validation = msg.validateMessage();

                        if (!"OK".equals(validation)) {
                            JOptionPane.showMessageDialog(null, validation);
                            i--; 
                            continue;
                        }

                        // show send/store/disregard options 
                        String result = msg.handleMessage();
                        if ("Message Sent".equals(result) || "Message Stored".equals(result)) {
                            
                            messages.add(msg);
                            manager.addMessage(msg);
                        } else {
                            
                        }
                    }

                    JOptionPane.showMessageDialog(null, "You have processed a total of " + messages.size() + " message(s) in this session.");
                }
                case 1 -> { // Manage Messages
                    manager.messageManagement();
                }
                default -> { // Logout 
                    loggedInMenu = false;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "You processed a total of " + messages.size() + " message(s). Goodbye!");
        
    }// End of main method 
    
}// End of Class 


/*
 * References:
  * Baeldung JUnit 5 Tutorial: https://www.baeldung.com/junit-5
 * Apache Maven Guide: https://maven.apache.org/guides/
 * GitHub Actions CI/CD Setup: https://docs.github.com/en/actions
   */
