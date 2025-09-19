/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RC_Student_lab
 */

public class Part1Test {

    //** Username tests **//
    
    @Test
    public void testValidUsername() {
        assertTrue(Part1.isUsernameValid("Des_1"));   // contains _ and <= 5
    }

    @Test
    public void testInvalidUsername_NoUnderscore() {
        assertFalse(Part1.isUsernameValid("Dest1")); // missing _
    }

    @Test
    public void testInvalidUsername_TooLong() {
        assertFalse(Part1.isUsernameValid("User_12")); // more than 5 chars
    }

    //** Password tests **//
    
    @Test
    public void testValidPassword() {
        assertTrue(Part1.isPasswordValid("Otaku19#")); // meets all conditions
    }

    @Test
    public void testInvalidPassword_NoUppercase() {
        assertFalse(Part1.isPasswordValid("otaku19#"));
    }

    @Test
    public void testInvalidPassword_NoDigit() {
        assertFalse(Part1.isPasswordValid("Otaku###"));
    }

    @Test
    public void testInvalidPassword_NoSpecialChar() {
        assertFalse(Part1.isPasswordValid("Otaku123"));
    }

    @Test
    public void testInvalidPassword_TooShort() {
        assertFalse(Part1.isPasswordValid("Ota1#"));
    }

    //** Phone tests **//
    
    @Test
    public void testValidPhone() {
        assertTrue(Part1.isPhoneValid("+27815950322")); // correct format
    }

    @Test
    public void testInvalidPhone_WrongPrefix() {
        assertFalse(Part1.isPhoneValid("0815950322")); // missing +27
    }

    @Test
    public void testInvalidPhone_NotEnoughDigits() {
        assertFalse(Part1.isPhoneValid("+27815950")); // too short
    }

    //** Register tests **//
    
    @Test
    public void testRegisterSuccess() {
        String result = Part1.register("Des_1", "Otaku19#", "+27815950322", "Destiny", "Eseh");
        assertEquals("User registered successfully.", result);
    }

    @Test
    public void testRegisterFail_InvalidUsername() {
        String result = Part1.register("Destiny", "Otaku19#", "+27815950322", "Destiny", "Eseh");
        assertTrue(result.contains("username"));
    }

    @Test
    public void testRegisterFail_InvalidPassword() {
        String result = Part1.register("Des_1", "otaku19", "+27815950322", "Destiny", "Eseh");
        assertTrue(result.contains("password"));
    }

    @Test
    public void testRegisterFail_InvalidPhone() {
        String result = Part1.register("Des_1", "Otaku19#", "0815950322", "Destiny", "Eseh");
        assertTrue(result.contains("phone"));
    }

    //** Login tests **//
    
    @Test
    public void testLoginSuccess() {
        Part1.register("Des_1", "Otaku19#", "+27815950322", "Destiny", "Eseh");
        assertTrue(Part1.login("Des_1", "Otaku19#"));
    }

    @Test
    public void testLoginFail_WrongPassword() {
        Part1.register("Des_1", "Otaku19#", "+27815950322", "Destiny", "Eseh");
        assertFalse(Part1.login("Des_1", "WrongPass#"));
    }

    @Test
    public void testLoginFail_WrongUsername() {
        Part1.register("Des_1", "Otaku19#", "+27815950322", "Destiny", "Eseh");
        assertFalse(Part1.login("Wrong_1", "Otaku19#"));
    }

    //** Login message tests **//
    
    @Test
    public void testLoginMessageSuccess() {
        Part1.register("Des_1", "Otaku19#", "+27815950322", "Destiny", "Eseh");
        boolean success = Part1.login("Des_1", "Otaku19#");
        String msg = Part1.loginMessage(success);
        assertEquals("Welcome Destiny Eseh, it is great to see you.", msg);
    }

    @Test
    public void testLoginMessageFailure() {
        Part1.register("Des_1", "Otaku19#", "+27815950322", "Destiny", "Eseh");
        boolean success = Part1.login("Wrong", "Wrong");
        String msg = Part1.loginMessage(success);
        assertEquals("Login failed. Username or password incorrect.", msg);
    }
    
}// end of tests