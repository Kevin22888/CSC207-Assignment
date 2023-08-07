import java.util.Scanner;

/**
 * The CipherProgramRunner class is responsible for running the encryption/decryption program.
 * It provides a command-line interface for the user to interact with the program and choose encryption techniques.
 */
public class CipherProgramRunner {

    private final Scanner scanner;
    private EncryptionService encryptionService;

    /**
     * Constructs a new instance of CipherProgramRunner.
     * Initializes the Scanner for user input and sets the default encryption service to Vigenere Cipher.
     */
    public CipherProgramRunner() {
        this.scanner = new Scanner(System.in);
        this.encryptionService = new VigenereCipherService(); // Default to Vigenere Cipher
    }

    /**
     * Starts the execution of the Cipher Program.
     * It displays the main menu, allows users to select encryption/decryption options, and runs the chosen operation.
     */
    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Select Cipher");
            System.out.println("4. Exit");

            int option;
            try {
                System.out.print("Enter the option number: ");
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid option number.");
                continue;
            }

            switch (option) {
                case 1:
                    // Encryption
                    handleEncryption();
                    break;

                case 2:
                    // Decryption
                    handleDecryption();
                    break;

                case 3:
                    // Select Cipher
                    selectCipher();
                    break;

                case 4:
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option! Please enter a valid option number.");
                    break;
            }
        }

        System.out.println("Goodbye!");
        scanner.close();
    }

    /**
     * Handles the encryption process.
     * It prompts the user for the message and key, performs encryption, and displays the result.
     */
    private void handleEncryption() {
        System.out.print("Enter the message to encrypt: ");
        String messageToEncrypt = KeyUtil.sanitizeInput(scanner.nextLine().toUpperCase());

        System.out.print("Enter the encryption key: ");
        String encryptionKey = KeyUtil.sanitizeInput(scanner.nextLine().toUpperCase());

        String encryptedMessage = encryptionService.encrypt(messageToEncrypt, encryptionKey);
        System.out.println("Encrypted Message: " + encryptedMessage);
    }

    /**
     * Handles the decryption process.
     * It prompts the user for the encrypted message and key, performs decryption, and displays the result.
     */
    private void handleDecryption() {
        System.out.print("Enter the message to decrypt: ");
        String messageToDecrypt = KeyUtil.sanitizeInput(scanner.nextLine().toUpperCase());

        System.out.print("Enter the decryption key: ");
        String decryptionKey = KeyUtil.sanitizeInput(scanner.nextLine().toUpperCase());

        String decryptedMessage = encryptionService.decrypt(messageToDecrypt, decryptionKey);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }

    /**
     * Allows the user to select an encryption technique.
     * It displays the available encryption options and sets the encryption service accordingly.
     */
    private void selectCipher() {
        System.out.println("Available Ciphers:");
        System.out.println("1. Vigenere Cipher");
        System.out.println("2. Columnar Transposition Cipher");
        System.out.print("Enter the number of the cipher you want to use: ");
        int cipherChoice;
        try {
            cipherChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid option number.");
            return;
        }

        if (cipherChoice == 1) {
            this.encryptionService = new VigenereCipherService();
        } else if (cipherChoice == 2) {
            this.encryptionService = new ColumnarTranspositionService();
        } else {
            System.out.println("Invalid cipher choice! Please select a valid cipher.");
        }
    }
}
