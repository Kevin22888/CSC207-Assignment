import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KeyUtilTest {

    @Test
    void sanitizeInput() {
        String text = "Text12 w4it42h41 n8u3m2b5e142r9876s.";
        assertEquals("TEXTWITHNUMBERS", KeyUtil.sanitizeInput(text));
    }
}