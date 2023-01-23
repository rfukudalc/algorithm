package test;

import main.RomanNumberStringifier;
import main.RomanNumberStringifierImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumberStringifierImplTest {

    final int intToConvert = 1024;
    final String expected = "MXXIV";

    final RomanNumberStringifier target = new RomanNumberStringifierImpl();

    @Nested
    class stringify {

        @Test
        void returnsRomanNumber() {
            var result = target.stringify(intToConvert);
            assertEquals(expected, result);
        }
    }
}
