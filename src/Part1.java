/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */

import java.util.Scanner;

public class Part1 {

    private static String registeredUsername;
    private static String registeredPassword;
    private static String registeredPhone;

    // Check if username is valid 
    public static boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Check password complexity 
    public static boolean checkPasswordComplexity(String password) {
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;

        if (password.length() < 8) {
            return false;
        }

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    // Check if cell phone number is valid 
    public static boolean checkCellPhoneNumber(String phone) {
        return phone.matches("0\\d{9}");
    }

    // Register user
    public static String registerUser(String username, String password, String phone) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(phone)) {
            return "Cell phone number is incorrectly formatted. Please ensure it contains 10 digits and starts with 0.";
        }

        registeredUsername = username;
        registeredPassword = password;
        registeredPhone = phone;

        return "User registered successfully.";
    }

    // Login user
    public static boolean loginUser(String username, String password) {
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    }

    // Return login status
    public static String returnLoginStatus(String firstName, String lastName, boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Main method 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter cell phone number: ");
        String phone = sc.nextLine();

        String registrationMessage = registerUser(username, password, phone);
        System.out.println(registrationMessage);

        if (registrationMessage.equals("User registered successfully.")) {
            System.out.println("\n=== User Login ===");
            System.out.print("Enter username: ");
            String loginUser = sc.nextLine();
            System.out.print("Enter password: ");
            String loginPass = sc.nextLine();

            boolean loginSuccess = loginUser(loginUser, loginPass);
            System.out.println(returnLoginStatus("Kyle", "Smith", loginSuccess));
        }

        sc.close();
        
    }// end of main method
    
} // end of main class
