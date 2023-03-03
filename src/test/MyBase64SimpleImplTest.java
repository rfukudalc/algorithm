package test;

import main.MyBase64Simple;
import main.MyBase64SimpleImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyBase64SimpleImplTest {

    final MyBase64Simple target = new MyBase64SimpleImpl();

    byte[] bytes = "Base64EncoderEncodeToString".getBytes(StandardCharsets.UTF_8);

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
