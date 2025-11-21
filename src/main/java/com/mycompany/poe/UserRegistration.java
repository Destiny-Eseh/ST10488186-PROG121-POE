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

public class UserRegistration {
    String registeredUser;
    String registeredPassword;
    String registeredPhone;

     boolean checkUserName(String username) {
        if (username == null) return false;
        return username.contains("_") && username.indexOf("_") <= 5;
    }

     boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        boolean length = password.length() >= 8;
        boolean capital = password.matches(".*[A-Z].*");
        boolean digit = password.matches(".*[0-9].*");
        boolean special = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        return length && capital && digit && special;
    }

    // ChatGPT generated cellphone checker 
    
    public boolean checkCellPhoneNumber(String phone) {
        if (phone == null) return false;
        return phone.matches("^\\+27\\d{9}$");
    }

    String registerUser(String username, String password, String phone) {
        if (!checkUserName(username)) {
            JOptionPane.showMessageDialog(null, "Username incorrectly formatted.\nMust contain '_' and ≤5 characters before it.");
            return "Invalid Username";
        }
        if (!checkPasswordComplexity(password)) {
            JOptionPane.showMessageDialog(null, "Password incorrectly formatted.\nMust have at least 8 chars, a capital letter, a number, and a special character.");
            return "Invalid Password";
        }
        if (!checkCellPhoneNumber(phone)) {
            JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted.\nMust start with +27 followed by 9 digits.");
            return "Invalid Phone";
        }

        this.registeredUser = username;
        this.registeredPassword = password;
        this.registeredPhone = phone;

        JOptionPane.showMessageDialog(null, "User registered successfully!");
        return "User registered successfully!";
    }

     boolean loginUser(String username, String password) {
        if (username == null || password == null) {
            JOptionPane.showMessageDialog(null, "Login failed. Incorrect username or password.");
            return false;
        }
        if (username.equals(this.registeredUser) && password.equals(this.registeredPassword)) {
            JOptionPane.showMessageDialog(null, "Login successful!\nWelcome " + username + "!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Incorrect username or password.");
            return false;
        }
        
    }// End of method
    
}// End of class 


// Refs:
// ChatGPT: Regex cell phone checker implemented
