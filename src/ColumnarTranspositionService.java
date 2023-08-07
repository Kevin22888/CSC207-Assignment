import java.util.Arrays;

/**
 * Implementation of the {@link EncryptionService} interface using the Columnar Transposition Cipher technique.
 * The Columnar Transposition Cipher is a method of rearranging the characters of a message to achieve encryption.
 */
public class ColumnarTranspositionService implements EncryptionService {

    @Override
    public String encrypt(String message, String key) {
        int[] keySequence = getKeySequence(key);
        int numCols = key.length();
        int numRows = message.length() % numCols == 0 ? message.length() / numCols : message.length() / numCols + 1;

        // Fill the matrix row by row with the message characters
        char[][] matrix = new char[numRows][numCols];
        int charIndex = 0;
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (charIndex < message.length()) {
                    char ch = message.charAt(charIndex);
                    if (Character.isLetter(ch)) {
                        matrix[row][col] = ch;
                        charIndex++;
                    } else {
                        // Fill non-letter characters with random letters (for simplicity, using 'X')
                        matrix[row][col] = 'X';
                        charIndex++;
                    }
                } else {
                    // Fill any remaining spots with random letters (for simplicity, using 'X')
                    matrix[row][col] = 'X';
                }
            }
        }

        // Extract the columns based on the key sequence and form the encrypted message
        String[] encryptedSequences = new String[numCols];
        for (int col = 0; col < numCols; col++) {
            StringBuilder sequence = new StringBuilder();
            for (int row = 0; row < numRows; row++) {
                sequence.append(matrix[row][col]);
            }
            encryptedSequences[col] = sequence.toString();
        }

        // Loop through the key sequence array and swap elements in both arrays accordingly
        for (int i = 0; i < keySequence.length - 1; i++) {
            for (int j = i + 1; j < keySequence.length; j++) {
                if (keySequence[i] > keySequence[j]) {
                    int tempOrder = keySequence[i];
                    keySequence[i] = keySequence[j];
                    keySequence[j] = tempOrder;

                    String tempString = encryptedSequences[i];
                    encryptedSequences[i] = encryptedSequences[j];
                    encryptedSequences[j] = tempString;
                }
            }
        }

        // Build the concatenated string using StringBuilder
        StringBuilder encryptedMessage = new StringBuilder();
        for (String str : encryptedSequences) {
            encryptedMessage.append(str);
        }

        return encryptedMessage.toString();
    }

    @Override
    public String decrypt(String encryptedMessage, String key) {
        int[] keySequence = getKeySequence(key);
        int numCols = key.length();
        int numRows = (int) Math.ceil((double) encryptedMessage.length() / numCols);

        // Fill the matrix with the encrypted message characters based on the key sequence
        char[][] matrix = new char[numRows][numCols];
        int charIndex = 0;
        for (int colIndex = 0; colIndex < numCols; colIndex++) {
            int targetIndex = -1;
            for (int i = 0; i < keySequence.length; i++) {
                if (keySequence[i] == colIndex + 1) {
                    targetIndex = i;
                    break;
                }
            }
            for (int row = 0; row < numRows; row++) {
                if (charIndex < encryptedMessage.length()) {
                    char ch = encryptedMessage.charAt(charIndex);
                    matrix[row][targetIndex] = ch; // Use the index of occurrence as the column index
                    charIndex++;
                }
            }
        }

        // Read the matrix row by row and form the decrypted message
        StringBuilder decryptedMessage = new StringBuilder();
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (matrix[row][col] != 0) {
                    decryptedMessage.append(matrix[row][col]);
                }
            }
        }

        return decryptedMessage.toString();
    }

    private int[] getKeySequence(String key) {
        // Convert the key to a sequence of integers, starting from 1 based on the first appearance in the alphabet
        int[] keySequence = new int[key.length()];
        char[] sortedKeyChars = key.toCharArray();
        Arrays.sort(sortedKeyChars);

        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            int position = Arrays.binarySearch(sortedKeyChars, ch) + 1;
            keySequence[i] = position;
        }

        return keySequence;
    }
}
