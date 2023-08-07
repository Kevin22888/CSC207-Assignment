/**
 * This interface defines the contract for encryption and decryption services.
 * Different implementations of this interface can provide various encryption techniques.
 */
public interface EncryptionService {

    /**
     * Encrypts the given message using the specified key.
     *
     * @param message The message to be encrypted.
     * @param key     The key used for encryption.
     * @return The encrypted message.
     */
    String encrypt(String message, String key);

    /**
     * Decrypts the given encrypted message using the specified key.
     *
     * @param encryptedMessage The encrypted message to be decrypted.
     * @param key              The key used for decryption.
     * @return The decrypted message.
     */
    String decrypt(String encryptedMessage, String key);
}