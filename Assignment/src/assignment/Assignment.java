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
        System.out.println("=================================");
        System.out.println("---LOGIN PAGE---");
        System.out.println("=================================");
        while (true) {
            System.out.println("Enter your username:");
            String enteredUsername = input.nextLine();

            System.out.println("Enter your password:");
            String enteredPassword = input.nextLine();

            boolean isLoggedIn = loginObj.loginUser(enteredUsername, enteredPassword);
            System.out.println(loginObj.returnLoginStatus(isLoggedIn));

            if (isLoggedIn) {
                // --- PART 2: QUICKCHAT INTEGRATION ---
                System.out.println("\nWelcome to QuickChat.");
                boolean running = true;

                while (running) {
                    System.out.println("\n--- QUICKCHAT MAIN MENU ---");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.print("Choose an option: ");

                    String choice = input.nextLine();

                    switch (choice) {
                        case "1":
                            System.out.print("Enter how many messages you wish to enter: ");
                            int numMessages;
                            try {
                                numMessages = Integer.parseInt(input.nextLine());
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid number. Please enter a valid integer.");
                                break;
                            }

                            for (int i = 0; i < numMessages; i++) {
                                System.out.println("\n--- Entering Message " + (i + 1) + " of " + numMessages + " ---");
                                
                                // Recipient Validation
                                System.out.print("Enter recipient cell number (e.g., +27718693002): ");
                                String recipient = input.nextLine();
                                
                                Message tempValidator = new Message("1234567890", i, recipient, "test");
                                System.out.println(tempValidator.checkRecipientCell());

                                if (!tempValidator.isValidRecipientCell()) {
                                    System.out.println("Please correct the number and try again.");
                                    i--; // Retry this message iteration
                                    continue;
                                }

                                // Message Length Validation
                                System.out.print("Enter your message (max 250 characters): ");
                                String messageText = input.nextLine();

                                Message msgTest = new Message("1234567890", i, recipient, messageText);
                                System.out.println(msgTest.checkMessageLength());

                                if (messageText.length() > 250) {
                                    i--; // Retry this message iteration
                                    continue;
                                } else {
                                    System.out.println("Message sent");
                                }

                                // Generate Message ID and Hash
                                String messageID = Message.generateMessageID();
                                Message newMessage = new Message(messageID, i, recipient, messageText);

                                System.out.println("Message Hash: " + newMessage.createMessageHash());

                                // Action Menu (Send, Disregard, Store)
                                String actionResult = newMessage.SentMessage(input);
                                System.out.println(actionResult);
                            }

                            // Summary Output
                            System.out.println("\n==================================");
                            System.out.println("SENT MESSAGES SUMMARY:");
                            System.out.println(Message.printMessages());
                            System.out.println("Total messages sent: " + Message.returnTotalMessages());
                            System.out.println("==================================");
                            break;

                        case "2":
                            System.out.println("Coming Soon.");
                            break;

                        case "3":
                            System.out.println("Exiting QuickChat. Goodbye!");
                            running = false;
                            break;

                        default:
                            System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    }
                }
                break;
            }
        }

        input.close();
    }
}