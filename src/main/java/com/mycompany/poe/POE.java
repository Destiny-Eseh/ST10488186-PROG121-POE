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

        if (reg.loginUser(lu, lp)) {
            JOptionPane.showMessageDialog(null, "Login successful!\nWelcome " + lu + "!");
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Incorrect username or password.");
        }
        
    }// end of main method
    
}// end of class
