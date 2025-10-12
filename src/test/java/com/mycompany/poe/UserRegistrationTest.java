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
        assertTrue(reg.userNameCheck("dest_"));
    }

    @Test
    void testInvalidUsername_NoUnderscore() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.userNameCheck("destiny"));
    }

    @Test
    void testInvalidUsername_TooLong() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.userNameCheck("desti_"));
    }

    @Test
    void testValidPassword() {
        UserRegistration reg = new UserRegistration();
        assertTrue(reg.passwordCheck("Destiny1!"));
    }

    @Test
    void testInvalidPassword_NoCapital() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.passwordCheck("destiny1!"));
    }

    @Test
    void testInvalidPassword_NoDigit() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.passwordCheck("Destiny!"));
    }

    @Test
    void testInvalidPassword_NoSpecialChar() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.passwordCheck("Destiny12"));
    }

    @Test
    void testInvalidPassword_TooShort() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.passwordCheck("Des1!"));
    }

    @Test
    void testValidPhone() {
        UserRegistration reg = new UserRegistration();
        assertTrue(reg.phoneCheck("+27834567890"));
    }

    @Test
    void testInvalidPhone_WrongPrefix() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.phoneCheck("0834567890"));
    }

    @Test
    void testInvalidPhone_WrongLength() {
        UserRegistration reg = new UserRegistration();
        assertFalse(reg.phoneCheck("+2783456789"));
    }

    @Test
    void testRegisterUser_Success() {
        UserRegistration reg = new UserRegistration();
        String result = reg.registerUser("dest_", "Destiny1!", "+27834567890");
        assertEquals("User registered successfully!", result);
    }

    @Test
    void testRegisterUser_InvalidUsername() {
        UserRegistration reg = new UserRegistration();
        String result = reg.registerUser("destiny", "Destiny1!", "+27834567890");
        assertTrue(result.contains("Username is not correctly formatted"));
    }

    @Test
    void testRegisterUser_InvalidPassword() {
        UserRegistration reg = new UserRegistration();
        String result = reg.registerUser("dest_", "destiny", "+27834567890");
        assertTrue(result.contains("Password is not correctly formatted"));
    }

    @Test
    void testRegisterUser_InvalidPhone() {
        UserRegistration reg = new UserRegistration();
        String result = reg.registerUser("dest_", "Destiny1!", "0834567890");
        assertTrue(result.contains("Cell phone number is not valid"));
    }

    @Test
    void testLoginUser_Success() {
        UserRegistration reg = new UserRegistration();
        reg.registerUser("dest_", "Destiny1!", "+27834567890");
        assertTrue(reg.loginUser("dest_", "Destiny1!"));
    }

    @Test
    void testLoginUser_Failure() {
        UserRegistration reg = new UserRegistration();
        reg.registerUser("dest_", "Destiny1!", "+27834567890");
        assertFalse(reg.loginUser("dest_", "WrongPass!"));
    }
}