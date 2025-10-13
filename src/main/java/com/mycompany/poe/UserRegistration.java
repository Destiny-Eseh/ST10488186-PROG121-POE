/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.poe;

/**
 *
 * @author RC_Student_Lab
 */
public class UserRegistration {
    String registeredUser;
    String registeredPassword;
    String registeredPhone;

    public boolean userNameCheck(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean passwordCheck(String password) {
        boolean length = password.length() >= 8;
        boolean capital = password.matches(".*[A-Z].*");
        boolean digit = password.matches(".*[0-9].*");
        boolean special = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        return length && capital && digit && special;
    }

    public boolean phoneCheck(String phone) {
        return phone.matches("\\+27\\d{9}");
    }

    public String registerUser(String username, String password, String phone) {
        if (!userNameCheck(username)) {
            return "Username is not correctly formatted. (It doesn't have 5 charathers or and underscore) ";
        }
        if (!passwordCheck(password)) {
            return "Password is not correctly formatted. (It should have 8 characters, a capital letter, a number, and a special character.) ";
        }
        if (!phoneCheck(phone)) {
            return "Cell phone number is not valid. (It must start with +27 and contain 9 digits.) ";
        }

        this.registeredUser = username;
        this.registeredPassword = password;
        this.registeredPhone = phone;
        return "User registered successfully!";
    }

    public boolean loginUser(String username, String password) {
        return username.equals(this.registeredUser) && password.equals(this.registeredPassword);
        
    }// end of main method
    
}// end of class 
