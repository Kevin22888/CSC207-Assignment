/**
 * Implementation of the {@link EncryptionService} interface using the Vigenere Cipher technique.
 * The Vigenere Cipher is a method of encrypting alphabetic text by using a simple form of polyalphabetic substitution.
 */
public class VigenereCipherService implements EncryptionService {

    @Override
    public String encrypt(String message, String key) {
        StringBuilder encrypted = new StringBuilder();
        int keyIndex = 0;

        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = key.charAt(keyIndex) - 'A';
                char encryptedChar = (char) (((c - 'A' + shift) % 26) + 'A');
                encrypted.append(encryptedChar);

                keyIndex = (keyIndex + 1) % key.length();
            } else {
                encrypted.append(c);
            }
        }

        return encrypted.toString();
    }

    @Override
    public String decrypt(String encryptedMessage, String key) {
        StringBuilder decrypted = new StringBuilder();
        int keyIndex = 0;

        for (char c : encryptedMessage.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = key.charAt(keyIndex) - 'A';
                char decryptedChar = (char) (((c - 'A' - shift + 26) % 26) + 'A');
                decrypted.append(decryptedChar);

                keyIndex = (keyIndex + 1) % key.length();
            } else {
                decrypted.append(c);
            }
        }

        return decrypted.toString();
    }
}