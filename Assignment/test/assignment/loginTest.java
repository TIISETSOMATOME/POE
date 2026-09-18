package assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class loginTest {

    private login userLogin;

    @BeforeEach
    public void setUp() {
        userLogin = new login();
    }

    @Test
    public void testSetFirstNameAndLastName() {
        userLogin.setFirstName("Kylie");
        userLogin.setLastName("Smith");
        userLogin.registerUser("kyl_1", "Ch3ss#123", "+27831234567");

        String status = userLogin.returnLoginStatus(true);
        assertEquals("Welcome Kylie, kyl_1 it is great to see you again.", status);
    }

    @Test
    public void testCheckUserNameSuccess() {
        // Contains '_' and length <= 5
        assertTrue(userLogin.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameFailureNoUnderscore() {
        assertFalse(userLogin.checkUserName("kylie"));
    }

    @Test
    public void testCheckUserNameFailureTooLong() {
        assertFalse(userLogin.checkUserName("k_ylie1"));
    }

    @Test
    public void testCheckPasswordComplexitySuccess() {
        // >= 8 chars, 1 uppercase, 1 digit, 1 special character
        assertTrue(userLogin.checkPasswordComplexity("Ch3ss#123"));
    }

    @Test
    public void testCheckPasswordComplexityFailureShort() {
        assertFalse(userLogin.checkPasswordComplexity("Ch3ss#"));
    }

    @Test
    public void testCheckPasswordComplexityFailureNoUpper() {
        assertFalse(userLogin.checkPasswordComplexity("ch3ss#123"));
    }

    @Test
    public void testCheckPasswordComplexityFailureNoDigit() {
        assertFalse(userLogin.checkPasswordComplexity("Chess#word"));
    }

    @Test
    public void testCheckPasswordComplexityFailureNoSpecial() {
        assertFalse(userLogin.checkPasswordComplexity("Ch3ssword1"));
    }

    @Test
    public void testCheckCellPhoneNumberSuccess() {
        assertTrue(userLogin.checkCellPhoneNumber("+27831234567"));
    }

    @Test
    public void testCheckCellPhoneNumberFailureNoPlus() {
        assertFalse(userLogin.checkCellPhoneNumber("27831234567"));
    }

    @Test
    public void testCheckCellPhoneNumberFailureInvalidChars() {
        assertFalse(userLogin.checkCellPhoneNumber("+2783abc123"));
    }

    @Test
    public void testRegisterUserSuccess() {
        String result = userLogin.registerUser("kyl_1", "Ch3ss#123", "+27831234567");
        assertEquals("The two above conditions have been met, and the user has been registered successfully.", result);
    }

    @Test
    public void testRegisterUserInvalidUsername() {
        String result = userLogin.registerUser("kylie", "Ch3ss#123", "+27831234567");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testRegisterUserInvalidPassword() {
        String result = userLogin.registerUser("kyl_1", "password", "+27831234567");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testRegisterUserInvalidPhoneNumber() {
        String result = userLogin.registerUser("kyl_1", "Ch3ss#123", "0831234567");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }

    @Test
    public void testLoginUserSuccess() {
        userLogin.registerUser("kyl_1", "Ch3ss#123", "+27831234567");
        assertTrue(userLogin.loginUser("kyl_1", "Ch3ss#123"));
    }

    @Test
    public void testLoginUserFailure() {
        userLogin.registerUser("kyl_1", "Ch3ss#123", "+27831234567");
        assertFalse(userLogin.loginUser("kyl_1", "WrongPass1!"));
    }

    @Test
    public void testReturnLoginStatusSuccess() {
        userLogin.setFirstName("Kylie");
        userLogin.registerUser("kyl_1", "Ch3ss#123", "+27831234567");
        
        String status = userLogin.returnLoginStatus(true);
        assertEquals("Welcome Kylie, kyl_1 it is great to see you again.", status);
    }

    @Test
    public void testReturnLoginStatusFailure() {
        String status = userLogin.returnLoginStatus(false);
        assertEquals("Username or password incorrect, please try again.", status);
    }
}