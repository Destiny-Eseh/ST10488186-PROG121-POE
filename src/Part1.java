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

    private static String savedUsername;
    private static String savedPassword;
    private static String savedPhone;
    private static String savedFirstName;
    private static String savedLastName;

    public static boolean isUsernameValid(String u) {
        return u.contains("_") && u.length() <= 5;
    }

    public static boolean isPasswordValid(String p) {
        if (p == null || p.length() < 8) return false;
        boolean upper = false, digit = false, special = false;
        for (char c : p.toCharArray()) {
            if (Character.isUpperCase(c)) upper = true;
            else if (Character.isDigit(c)) digit = true;
            else if (!Character.isLetterOrDigit(c)) special = true;
        }
        return upper && digit && special;
    }

    public static boolean isPhoneValid(String phone) {
        // +27 followed by 9 digits → total characters including + is 12
        return phone != null && phone.matches("\\+27\\d{9}");
    }

    public static String register(String username, String password, String phone,
                                  String firstName, String lastName) {
        if (!isUsernameValid(username)) {
            return "The username is not valid. It must be no more than 5 characters long and include an _";
        }
        if (!isPasswordValid(password)) {
            return "The password is not valid. It must contain a capital letter(A-Z), a number (1-10), and a special character (#-$) and have a minimum of eight characters( 8).";
        }
        if (!isPhoneValid(phone)) {
            return "The phone number is not valid. It must have a total of 10 digits and begin with +27.";
        }

        savedUsername = username;
        savedPassword = password;
        savedPhone = phone;
        savedFirstName = firstName;
        savedLastName = lastName;

        return "User registered successfully.";
    }

    public static boolean login(String username, String password) {
        if (savedUsername == null) return false;
        return savedUsername.equals(username) && savedPassword.equals(password);
    }

    public static String welcomeMessage() {
        return "Welcome " + savedFirstName + " " + savedLastName + ", it is great to see you.";
    }

    public static String loginMessage(boolean success) {
        if (success) return welcomeMessage();
        return "Login failed. Username or password incorrect.";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String first = sc.nextLine().trim();
        System.out.print("Enter last name: ");
        String last = sc.nextLine().trim();

        System.out.print("Choose a username (include _ and max 5 chars): ");
        String username = sc.nextLine().trim();
        System.out.print("Create a password: ");
        String password = sc.nextLine();
        System.out.print("Enter phone : ");
        String phone = sc.nextLine().trim();

        String reg = register(username, password, phone, first, last);
        System.out.println(reg);

        if (reg.equals("User registered successfully.")) {
            System.out.print("Login: username: ");
            String lu = sc.nextLine().trim();
            System.out.print("Login: password: ");
            String lp = sc.nextLine();

            boolean ok = login(lu, lp);
            System.out.println(loginMessage(ok));
        }

        sc.close();
        
    } // end main method
    
} // end main class


/*
 * References:
 * W3Schools Java Regex Tutorial: https://www.w3schools.com/java/java_regex.asp
 * ChatGPT assistance for phone number regex and validation.
 */