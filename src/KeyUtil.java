public class KeyUtil {

    public static String sanitizeInput(String key) {
        // Remove all non-alphabetic characters from the key using regular expressions
        return key.replaceAll("[^A-Za-z]", "").toUpperCase();
    }
}