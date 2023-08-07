import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ColumnarTranspositionServiceTest {
    @Test
    public void testColumnarTranspositionEncryptionAndDecryption() {
        EncryptionService columnarTranspositionService = new ColumnarTranspositionService();
        String message = "INANOTHERUNIVERSE";
        String key = "WORLD";

        // Test encryption
        String encryptedMessage = columnarTranspositionService.encrypt(message, key);
        Assertions.assertEquals("OURXNREXNHIEAEVXITNS", encryptedMessage);

        // Test decryption
        String decryptedMessage = columnarTranspositionService.decrypt(encryptedMessage, key);
        Assertions.assertEquals("INANOTHERUNIVERSEXXX", decryptedMessage);

        // Test consistency of encryption and decryption
        String reEncryptedMessage = columnarTranspositionService.encrypt(decryptedMessage, key);
        Assertions.assertEquals(encryptedMessage, reEncryptedMessage);
    }
}
