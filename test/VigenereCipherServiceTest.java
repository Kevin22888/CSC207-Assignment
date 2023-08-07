import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VigenereCipherServiceTest {
    @Test
    public void testVigenereCipherEncryptionAndDecryption() {
        EncryptionService vigenereCipherService = new VigenereCipherService();
        String message = "THIS IS A MESSAGE";
        String key = "WORLD";

        // Test encryption
        String encryptedMessage = vigenereCipherService.encrypt(message, key);
        Assertions.assertEquals("PVZD LO O DPVOOXP", encryptedMessage);

        // Test decryption
        String decryptedMessage = vigenereCipherService.decrypt(encryptedMessage, key);
        Assertions.assertEquals("THIS IS A MESSAGE", decryptedMessage);

        // Test consistency of encryption and decryption
        String reEncryptedMessage = vigenereCipherService.encrypt(decryptedMessage, key);
        Assertions.assertEquals(encryptedMessage, reEncryptedMessage);
    }
}
