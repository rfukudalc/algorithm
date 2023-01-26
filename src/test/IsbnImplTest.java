package test;

import main.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IsbnImplTest {

    final String prefix = "978";
    final String group = "92";
    final String registrant = "95055";
    final String publication = "12";
    final String checkDigit = "4";

    private final IsbnImpl isbnA1 = new IsbnImpl(prefix, group, registrant, publication, checkDigit);
    private final IsbnImpl isbnA2 = new IsbnImpl(prefix, group, registrant, publication, checkDigit);

    final main.Isbn isbnToPut = new IsbnImpl(prefix, group, registrant, publication, checkDigit);
    final main.Isbn isbnToGet = new IsbnImpl(prefix, group, registrant, publication, checkDigit);

    @Nested
    class Isbn {

        @Test
        void putAndGetIsbn() {
            var map = new MyMapImpl<main.Isbn, String>(100);

            var book = "細雪";
            map.put(isbnToPut, book);

            var result = map.get(isbnToGet);
            assertEquals(book, result);
        }
    }

    @Nested
    class equals {
        @Test
        void returnsTrueWhenBothObjectsAreEqual() {
            assertEquals(
                    new IsbnImpl(prefix, group, registrant, publication, checkDigit),
                    new IsbnImpl(prefix, group, registrant, publication, checkDigit)
            );
        }

        @Test
        void returnsTrueWhenBothObjectsAreNotEqual() {
            assertNotEquals(
                    new IsbnImpl(prefix, group, registrant, publication, checkDigit),
                    new IsbnImpl(prefix, group, registrant, publication, "7")
            );
        }
    }

    @Nested
    class hashCode {
        @Test
        void returnsConsistentHashCode() {
            assertEquals(isbnA1.hashCode(), isbnA2.hashCode());
        }
    }
}
