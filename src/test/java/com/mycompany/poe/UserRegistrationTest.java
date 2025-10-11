/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author RC_Student_Lab
 */
public class UserRegistrationTest {

    @Test
    void testValidUsername() {
        UserRegistration reg = new UserRegistration();
        assertTrue(reg.userNameCheck("abc_"));
    }

    @Test
    void testInvalidUsername_NoUnderscore() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.userNameCheck("abcde"));
    }

    @Test
    void testInvalidUsername_TooLong() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.userNameCheck("abc_de"));
    }

    
    @Test
    void testValidPassword() {
        UserRegistration reg = new UserRegistration();
        assertTrue(reg.passwordCheck("Abcdef1!"));
    }

    @Test
    void testInvalidPassword_NoCapital() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.passwordCheck("abcdef1!"));
    }

    @Test
    void testInvalidPassword_NoDigit() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.passwordCheck("Abcdefg!"));
    }

    @Test
    void testInvalidPassword_NoSpecialChar() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.passwordCheck("Abcdef12"));
    }

    @Test
    void testInvalidPassword_TooShort() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.passwordCheck("Ab1!"));
    }

    @Test
    void testValidPhone() {
        UserRegistration reg = new UserRegistration();
        assertTrue(reg.phoneCheck("+27812345678"));
    }

    @Test
    void testInvalidPhone_WrongPrefix() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.phoneCheck("0812345678"));
    }

    @Test
    void testInvalidPhone_WrongLength() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.phoneCheck("+2781234567"));
    }

    @Test
    void testRegisterUser_Success() {
        UserRegistration reg = new UserRegistration();
        String result = reg.registerUser("abc_", "Abcdef1!", "+27812345678");
        assertEquals("User registered successfully!", result);
    }

    @Test
    void testRegisterUser_InvalidUsername() {
        UserRegistration reg = new UserRegistration();
        String result = reg.registerUser("abcdef", "Abcdef1!", "+27812345678");
        assertTrue(result.contains("Username is not correctly formatted"));
    }

    @Test
    void testRegisterUser_InvalidPassword() {
        UserRegistration reg = new UserRegistration();
        String result = reg.registerUser("abc_", "abcdefg", "+27812345678");
        assertTrue(result.contains("Password is not correctly formatted"));
    }

    @Test
    void testRegisterUser_InvalidPhone() {
        UserRegistration reg = new UserRegistration();
        String result = reg.registerUser("abc_", "Abcdef1!", "0812345678");
        assertTrue(result.contains("Cell phone number is not valid"));
    }

    @Test
    void testLoginUser_Success() {
        UserRegistration reg = new UserRegistration();
        reg.registerUser("abc_", "Abcdef1!", "+27812345678");
        assertTrue(reg.loginUser("abc_", "Abcdef1!"));
    }

    @Test
    void testLoginUser_Failure() {
        UserRegistration reg = new UserRegistration();
        reg.registerUser("abc_", "Abcdef1!", "+27812345678");
        assertFalse(reg.loginUser("abc_", "WrongPass"));
    }
}
