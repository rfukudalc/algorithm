package test;

import main.MyTreeMap;
import main.MyTreeMapImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyTreeMapImplTest {

    final MyTreeMap<Integer, String> myTreeMap = new MyTreeMapImpl<>(Comparator.naturalOrder());

    @Nested
    class putAndGet {

        @Test
        void testPutAndGetMethods() {
            put(1000);
            put(0);
            put(2000);
            put(1);
            put(1500);
            put(3000);
            put(500);
            put(1250);
            put(1800);
            put(250);
            put(1700);
            put(1900);
            put(2);
            put(270);
            put(1850);
            put(1950);
            put(9);
            put(260);
            put(300);

            assertEquals(19, myTreeMap.size());

            assertEquals("1000", myTreeMap.get(1000));
            assertEquals("0", myTreeMap.get(0));
            assertEquals("2000", myTreeMap.get(2000));
            assertEquals("1", myTreeMap.get(1));
            assertEquals("1500", myTreeMap.get(1500));
            assertEquals("3000", myTreeMap.get(3000));
            assertEquals("500", myTreeMap.get(500));
            assertEquals("1250", myTreeMap.get(1250));
            assertEquals("1800", myTreeMap.get(1800));
            assertEquals("250", myTreeMap.get(250));
            assertEquals("1700", myTreeMap.get(1700));
            assertEquals("1900", myTreeMap.get(1900));
            assertEquals("2", myTreeMap.get(2));
            assertEquals("270", myTreeMap.get(270));
            assertEquals("1850", myTreeMap.get(1850));
            assertEquals("1950", myTreeMap.get(1950));
            assertEquals("9", myTreeMap.get(9));
            assertEquals("260", myTreeMap.get(260));
            assertEquals("300", myTreeMap.get(300));
        }
    }

    {
        put(1000);
        put(0);
        put(2000);
        put(1);
        put(1500);
        put(3000);
        put(500);
        put(1250);
        put(1800);
        put(250);
        put(1700);
        put(1900);
        put(2);
        put(270);
        put(1850);
        put(1950);
        put(9);
        put(260);
        put(300);
    }

    @Nested
    class removeAndGet {

        @Test
        void testRemoveAndGetMethods() {
            myTreeMap.remove(1900);
            myTreeMap.remove(1250);
            myTreeMap.remove(270);
            myTreeMap.remove(500);
            myTreeMap.remove(1500);
            myTreeMap.remove(1000);
            myTreeMap.remove(2);
            myTreeMap.remove(2);
            myTreeMap.remove(2);
            myTreeMap.remove(250);

            assertEquals(11, myTreeMap.size());

            assertEquals("0", myTreeMap.get(0));
            assertEquals("2000", myTreeMap.get(2000));
            assertEquals("1", myTreeMap.get(1));
            assertNull(myTreeMap.get(1500));
            assertEquals("3000", myTreeMap.get(3000));
            assertNull(myTreeMap.get(500));
            assertNull(myTreeMap.get(1250));
            assertEquals("1800", myTreeMap.get(1800));
            assertNull(myTreeMap.get(250));
            assertEquals("1700", myTreeMap.get(1700));
            assertNull(myTreeMap.get(1900));
            assertNull(myTreeMap.get(2));
            assertNull(myTreeMap.get(270));
            assertEquals("1850", myTreeMap.get(1850));
            assertEquals("1950", myTreeMap.get(1950));
            assertEquals("9", myTreeMap.get(9));
            assertEquals("260", myTreeMap.get(260));
            assertEquals("300", myTreeMap.get(300));
        }
    }

    @Nested
    class forEach {
        @Test
        void testForEachMethod() {
            myTreeMap.forEach((k, v) -> System.out.println(k + " : " + v));
        }
    }

    @Nested
    class size {
        // The size() method is tested in other tests
    }

    private void put(int key) {
        myTreeMap.put(key, String.valueOf(key));
    }
}
