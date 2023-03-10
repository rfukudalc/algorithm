package test;

import main.StringCompressor;
import main.StringCompressorImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCompressorImplTest {

    final String input = "AAAAAAABBBBBLLLLDZZZ";

    final String expected= "A7B5L4D1Z3";

    final StringCompressor target = new StringCompressorImpl();

    @Nested
    class compressString {

        @Test
        void returnsCompressedString() {
            var result = target.compressString(input);

            assertEquals(expected, result);
        }
    }

    @Nested
    class compressStringWithStreamApi {

        @Test
        void returnsCompressedStringViaStreamApi() {
            var result = target.compressStringWithStreamApi(input);

            assertEquals(expected, result);
        }
    }
}
