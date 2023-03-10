package test;

import main.StringDecompressor;
import main.StringDecompressorImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringDecompressorImplTest {

    final String input = "B4E2H1L4";

    final String expected = "BBBBEEHLLLL";

    final StringDecompressor target = new StringDecompressorImpl();

    @Nested
    class decompressString {

        @Test
        void returnsDecompressedString() {
            var result = target.decompressString(input);

            assertEquals(expected, result);
        }

        @Test
        void returnsDecompressedStringViaStreamApi() {
            var result = target.decompressStringWithStreamApi(input);

            assertEquals(expected, result);
        }
    }
}
