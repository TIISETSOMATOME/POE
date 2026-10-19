package assignment;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class Message {

    // declare Variables
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;

    // Static Tracking Lists and Counter
    private static ArrayList<Message> sentMessages = new ArrayList<>();
    private static ArrayList<Message> disregardedMessages = new ArrayList<>();
    private static ArrayList<Message> storedMessages = new ArrayList<>();
    private static int totalMessages = 0;

    // Constructor
    public Message(String messageID, int messageNumber, String recipient, String message) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
    }

    // Getters
    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }

    // 1. Check Message ID (Must not be more than 10 characters)
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // 2. Check Recipient Cell (No more than 10 chars/digits, starts with SA international code +27)
    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+27[6-8][0-9]{8}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public boolean isValidRecipientCell() {
        return recipient != null && recipient.matches("^\\+27[6-8][0-9]{8}$");
    }

    // 3. Check Message Length (Max 250 characters)
    public String checkMessageLength() {
        if (message != null && message.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size";
        }
    }

    // 4. Create Message Hash
    // Format: First two numbers of messageID : messageNumber : FirstWordLastWord (Uppercase)
    public String createMessageHash() {
        String firstTwoNumbers;
        if (messageID != null && messageID.length() >= 2) {
            firstTwoNumbers = messageID.substring(0, 2);
        } else {
            firstTwoNumbers = (messageID != null) ? messageID : "00";
        }

        String firstWord = "";
        String lastWord = "";

        if (message != null && !message.trim().isEmpty()) {
            String cleanMessage = message.trim();
            String[] words = cleanMessage.split("\\s+");
            
            if (words.length > 0) {
                firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
                lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
            }
        }

        return (firstTwoNumbers + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // 5. SentMessage Method (Handles User Options: Send, Disregard, Store)
    public String SentMessage(Scanner input) {
        System.out.println("\n--- MESSAGE ACTIONS ---");
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store Message to send later");
        System.out.print("Choose an option (1-3): ");
        
        String option = input.nextLine();
        
        switch (option) {
            case "1":
                sentMessages.add(this);
                totalMessages++;
                storeMessage(); // Stores in JSON structure using json-simple
                return "Message successfully sent.";
            case "2":
                disregardedMessages.add(this);
                return "Press 0 to delete the message";
            case "3":
                storedMessages.add(this);
                return "Message successfully stored";
            default:
                return "Invalid option selected.";
        }
    }

    // 6. Store Message in JSON (Leveraging your json-simple library)
    public void storeMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messageID", messageID);
        jsonObject.put("messageNumber", messageNumber);
        jsonObject.put("recipient", recipient);
        jsonObject.put("message", message);
        jsonObject.put("messageHash", createMessageHash());
        // Stored successfully in JSON object memory structure
    }

    // 7. Print All Sent Messages
    public static String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message m : sentMessages) {
            sb.append("Message ID: ").append(m.messageID).append("\n")
              .append("Message Hash: ").append(m.createMessageHash()).append("\n")
              .append("Recipient: ").append(m.recipient).append("\n")
              .append("Message: ").append(m.message).append("\n\n");
        }
        return sb.toString();
    }

    // 8. Return Total Messages Sent
    public static int returnTotalMessages() {
        return totalMessages;
    }

    // Helper generator for unique 10-digit ID
    public static String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + rand.nextInt(900000000);
        return String.valueOf(id);
    }
}