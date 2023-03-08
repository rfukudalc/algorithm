package test;

import main.CharacterReplacer;
import main.CharacterReplacerImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterReplacerImplTest {

    final String input = "EFGK.efil (5628)";

    final String expected = "FGHL.fgjm (6739)";

    final CharacterReplacer target = new CharacterReplacerImpl();

    @Nested
    class replaceCharacters {

        @Test
        void returnsReplacedCharacters() {
            var result = target.replaceCharacters(input);

            assertEquals(expected, result);
        }

        @Test
        void returnsReplacedCharactersViaStreamApi() {
            var result = target.replaceCharactersWithStreamApi(input);

            assertEquals(expected, result);
        }
    }
}
