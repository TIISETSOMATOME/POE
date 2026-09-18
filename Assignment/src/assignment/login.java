package assignment;

public class login {
    
    // Variables to store user data
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String phoneNumber;

    // Setters for names so we can use them in the welcome message later
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Checks if username contains an underscore and is <= 5 characters
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Checks password for length, uppercase, number, and special character
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // Regex Cell Phone Checker
    // Reference: Adapted from standard international phone regex rules to meet POE requirements.
    // Checks for a '+' sign, 1 to 3 digits for country code, and up to 10 digits for the number.
    public boolean checkCellPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\+\\d{1,3}\\d{1,10}$");
    }

    // Returns registration messaging based on formatting conditions
    public String registerUser(String username, String password, String phoneNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        } else if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        } else if (!checkCellPhoneNumber(phoneNumber)) {
             return "Cell phone number incorrectly formatted or does not contain international code.";
        } else {
            // Save details if everything is valid
            this.username = username;
            this.password = password;
            this.phoneNumber = phoneNumber;
            return "The two above conditions have been met, and the user has been registered successfully.";
        }
    }

    // Verifies if login details match stored details
    public boolean loginUser(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }

    // Returns login status messages
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + username + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}