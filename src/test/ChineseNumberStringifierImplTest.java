package test;

import main.ChineseNumberStringifier;
import main.ChineseNumberStringifierImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChineseNumberStringifierImplTest {

    final ChineseNumberStringifier target = new ChineseNumberStringifierImpl();

    final String expected = "肆萬伍阡壱拾参";
    final int convertedInt = 45013;

    @Nested
    class stringify {

        @Test
        void returnsStringifyNumber() {
            var result = target.stringify(convertedInt);

            assertEquals(expected, result);
        }
    }
}
