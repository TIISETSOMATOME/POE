package assignment;

import java.util.Scanner;

public class Assignment {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        login loginObj = new login(); // Create our login manager object
        
        System.out.println("==========================");
        System.out.println("     --REGISTRATION--     ");
        System.out.println("==========================");
        System.out.println("Enter user first name:");
        String name = input.nextLine();
        loginObj.setFirstName(name);

        System.out.println("Enter user last name:");
        String surname = input.nextLine();
        loginObj.setLastName(surname);

        String phoneNumber;
        while (true) {
            System.out.println("Enter South African cell phone number (e.g. +27838968976):");
            phoneNumber = input.nextLine();

            if (loginObj.checkCellPhoneNumber(phoneNumber)) {
                System.out.println("Cell phone number successfully added.");
                break;
            } else {
                System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            }
        }

        String username;
        while (true) {
            System.out.println("Enter username:");
            username = input.nextLine();

            if (loginObj.checkUserName(username)) {
                System.out.println("Username successfully captured.");
                break;
            } else {
                System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            }
        }

        String password;
        while (true) {
            System.out.println("Enter user password:");
            password = input.nextLine();

            if (loginObj.checkPasswordComplexity(password)) {
                System.out.println("Password successfully captured.");
                break;
            } else {
                System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }

        // Finalize Registration
        
        System.out.println("\n--- REGISTRATION STATUS ---");
        
        String regMessage = loginObj.registerUser(username, password, phoneNumber);
        System.out.println(regMessage);

        // LOGIN FEATURE
        System.out.println("==========================");
        System.out.println("     ---LOGIN PAGE---     ");
        System.out.println("==========================");
        while (true) {
            System.out.println("Enter your username:");
            String enteredUsername = input.nextLine();

            System.out.println("Enter your password:");
            String enteredPassword = input.nextLine();

            boolean isLogged = loginObj.loginUser(enteredUsername, enteredPassword);
            System.out.println(loginObj.returnLoginStatus(isLogged));

            if (isLogged) {
                break;
            }
        }

        input.close();
    }
}