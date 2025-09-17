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

    public Part1Test() {
    }

    @Test
    public void testCheckUserName_Valid() {
        assertTrue(Part1.checkUserName("Ky_1"));
    }

    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(Part1.checkUserName("Kyle"));
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(Part1.checkPasswordComplexity("Ch!cks@nd88"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        assertFalse(Part1.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumber_Valid() {
        assertTrue(Part1.checkCellPhoneNumber("0789456789"));
    }

    @Test
    public void testCheckCellPhoneNumber_Invalid() {
        assertFalse(Part1.checkCellPhoneNumber("098765"));
    }

    @Test
    public void testRegisterUser() {
        String result = Part1.registerUser("Kyle_", "Ch!cks@nd88", "0789456789");
        assertEquals("User registered successfully.", result);
    }

    @Test
    public void testLoginUser_Success() {
        Part1.registerUser("Kyle_", "Ch!cks@nd88", "0789456789");
        boolean result = Part1.loginUser("Kyle_", "Ch!cks@nd88");
        assertTrue(result);
    }

    @Test
    public void testLoginUser_Failure() {
        Part1.registerUser("Kyle_", "Ch!cks@nd88", "0789456789");
        boolean result = Part1.loginUser("Kyle_", "wrongPass");
        assertFalse(result);
    }

    @Test
    public void testReturnLoginStatus_Success() {
        // Updated expected string exactly matches the method's return
        String message = Part1.returnLoginStatus("Kyle", "Smith", true);
        assertEquals("Welcome Kyle Smith, it is great to see you.", message);
    }

    @Test
    public void testReturnLoginStatus_Failure() {
        // Updated expected string exactly matches the method's return
        String message = Part1.returnLoginStatus("Kyle", "Smith", false);
        assertEquals("Username or password incorrect, please try again.", message);
    }
}
