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
            assertNull(myTreeMap.put(1000, "VALUE_1000"));
            assertNull(myTreeMap.put(0, "VALUE_0"));
            assertNull(myTreeMap.put(2000, "VALUE_2000"));
            assertNull(myTreeMap.put(1, "VALUE_1"));
            assertNull(myTreeMap.put(1500, "VALUE_1500"));
            assertNull(myTreeMap.put(3000, "VALUE_3000"));
            assertNull(myTreeMap.put(500, "VALUE_500"));
            assertNull(myTreeMap.put(1250, "VALUE_1250"));
            assertNull(myTreeMap.put(1800, "VALUE_1800"));
            assertNull(myTreeMap.put(250, "VALUE_250"));
            assertNull(myTreeMap.put(1700, "VALUE_1700"));
            assertNull(myTreeMap.put(1900, "VALUE_1900"));
            assertNull(myTreeMap.put(2, "VALUE_2"));
            assertNull(myTreeMap.put(270, "VALUE_270"));
            assertNull(myTreeMap.put(1850, "VALUE_1850"));
            assertNull(myTreeMap.put(1950, "VALUE_1950"));
            assertNull(myTreeMap.put(9, "VALUE_9"));
            assertNull(myTreeMap.put(260, "VALUE_260"));
            assertNull(myTreeMap.put(300, "VALUE_300"));

            assertEquals("VALUE_2000", myTreeMap.put(2000, "ALTERED_VALUE_2000"));

            assertEquals(19, myTreeMap.size());

            assertEquals("VALUE_1000", myTreeMap.get(1000));
            assertEquals("VALUE_0", myTreeMap.get(0));
            assertEquals("VALUE_2000", myTreeMap.get(2000));
            assertEquals("VALUE_1", myTreeMap.get(1));
            assertEquals("VALUE_1500", myTreeMap.get(1500));
            assertEquals("VALUE_3000", myTreeMap.get(3000));
            assertEquals("VALUE_500", myTreeMap.get(500));
            assertEquals("VALUE_1250", myTreeMap.get(1250));
            assertEquals("VALUE_1800", myTreeMap.get(1800));
            assertEquals("VALUE_250", myTreeMap.get(250));
            assertEquals("VALUE_1700", myTreeMap.get(1700));
            assertEquals("VALUE_1900", myTreeMap.get(1900));
            assertEquals("VALUE_2", myTreeMap.get(2));
            assertEquals("VALUE_270", myTreeMap.get(270));
            assertEquals("VALUE_1850", myTreeMap.get(1850));
            assertEquals("VALUE_1950", myTreeMap.get(1950));
            assertEquals("VALUE_9", myTreeMap.get(9));
            assertEquals("VALUE_260", myTreeMap.get(260));
            assertEquals("VALUE_300", myTreeMap.get(300));
        }
    }

    @Nested
    class removeAndGet {

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

        @Test
        void testRemoveAndGetMethods() {
            assertEquals("VALUE_1900", myTreeMap.remove(1900));
            assertEquals("VALUE_1250", myTreeMap.remove(1250));
            assertEquals("VALUE_270", myTreeMap.remove(270));
            assertEquals("VALUE_500", myTreeMap.remove(500));
            assertEquals("VALUE_1500", myTreeMap.remove(1500));
            assertEquals("VALUE_1000", myTreeMap.remove(1000));
            assertEquals("VALUE_2", myTreeMap.remove(2));
            assertEquals("VALUE_250", myTreeMap.remove(250));

            assertNull(myTreeMap.remove(2));

            assertEquals(11, myTreeMap.size());

            assertEquals("VALUE_0", myTreeMap.get(0));
            assertEquals("VALUE_2000", myTreeMap.get(2000));
            assertEquals("VALUE_1", myTreeMap.get(1));
            assertNull(myTreeMap.get(1500));
            assertEquals("VALUE_3000", myTreeMap.get(3000));
            assertNull(myTreeMap.get(500));
            assertNull(myTreeMap.get(1250));
            assertEquals("VALUE_1800", myTreeMap.get(1800));
            assertNull(myTreeMap.get(250));
            assertEquals("VALUE_1700", myTreeMap.get(1700));
            assertNull(myTreeMap.get(1900));
            assertNull(myTreeMap.get(2));
            assertNull(myTreeMap.get(270));
            assertEquals("VALUE_1850", myTreeMap.get(1850));
            assertEquals("VALUE_1950", myTreeMap.get(1950));
            assertEquals("VALUE_9", myTreeMap.get(9));
            assertEquals("VALUE_260", myTreeMap.get(260));
            assertEquals("VALUE_300", myTreeMap.get(300));
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
        myTreeMap.put(key, "VALUE_" + key);
    }
}
