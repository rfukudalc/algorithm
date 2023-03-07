package test;

import main.MyBase64;
import main.MyBase64Impl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyBase64ImplTest {

    final MyBase64 target = new MyBase64Impl();

    byte[] bytes = "Base64Encoding".getBytes(StandardCharsets.UTF_8);

    @Nested
    class encodeToString {

        final String expected = Base64.getEncoder().encodeToString(bytes);

        @Test
        void returnsEncodedString() {
            var result = target.encodeToString(bytes);

            assertEquals(expected, result);
        }
    }
}
