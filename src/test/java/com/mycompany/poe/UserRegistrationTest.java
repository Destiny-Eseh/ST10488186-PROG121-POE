/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author RC_Student_Lab
 */


public class UserRegistrationTest {

    private UserRegistration reg;

    @BeforeEach
    void setUp() {
        reg = new UserRegistration();
    }

    // USERNAME TESTS // 

    @Test
    void testCorrectUsernameFormat() {
        assertTrue(reg.checkUserName("des_1"));
    }

    @Test
    void testIncorrectUsernameFormat() {
        assertFalse(reg.checkUserName("destiny!!"));
    }

    // PASSWORD TESTS // 

    @Test
    void testPasswordMeetsComplexity() {
        assertTrue(reg.checkPasswordComplexity("De$tiny123!"));
    }

    @Test
    void testPasswordFailsComplexity() {
        assertFalse(reg.checkPasswordComplexity("password"));
    }

    // CELLPHONE NUMBER TESTS // 

    @Test
    void testCorrectPhoneNumber() {
        assertTrue(reg.checkCellPhoneNumber("+27815950322"));
    }

    @Test
    void testIncorrectPhoneNumber() {
        assertFalse(reg.checkCellPhoneNumber("08159503"));
    }

    // REGISTRATION TESTS // 

    @Test
    void testRegisterUserSuccess() {
        String result = reg.registerUser(
                "des_1",
                "De$tiny123!",
                "+27815950322"
        );
        assertEquals("User registered successfully!", result);
    }

    @Test
    void testRegisterUserInvalidUsername() {
        String result = reg.registerUser(
                "destiny",
                "De$tiny123!",
                "+27815950322"
        );
        assertEquals("Invalid Username", result);
    }

    @Test
    void testRegisterUserInvalidPassword() {
        String result = reg.registerUser(
                "des_1",
                "hello123",
                "+27815950322"
        );
        assertEquals("Invalid Password", result);
    }

    @Test
    void testRegisterUserInvalidPhone() {
        String result = reg.registerUser(
                "des_1",
                "De$tiny123!",
                "000000"
        );
        assertEquals("Invalid Phone", result);
    }

    // LOGIN TESTS // 

    @Test
    void testLoginSuccess() {
        reg.registerUser("des_1", "De$tiny123!", "+27815950322");
        assertTrue(reg.loginUser("des_1", "De$tiny123!"));
    }

    @Test
    void testLoginFailed() {
        reg.registerUser("des_1", "De$tiny123!", "+27815950322");
        assertFalse(reg.loginUser("des_1", "wrongPassword"));
    }
}

