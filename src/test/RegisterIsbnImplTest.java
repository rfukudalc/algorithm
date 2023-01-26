package test;

import main.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterIsbnImplTest {

    final String prefix = "978";
    final String group = "92";
    final String registrant = "95055";
    final String publication = "12";
    final String checkDigit = "4";

    final String bookName = "bookName";

    final IsbnImpl isbn = new IsbnImpl(prefix, group, registrant, publication, checkDigit);

    final int initialCapacity = 16;
    final MyMap map = new MyMapImpl(initialCapacity);

    final RegisterIsbn target = new RegisterIsbnImpl();

    @Nested
    class Isbn {

        @Test
        void createIsbn() {
            var result = target.register(map, isbn, bookName);
            assertEquals(bookName, result);
        }
    }
}
