package test;

import main.MyTreeMap;
import main.MyTreeMapImpl;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

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

            assertEquals("VALUE_2000", myTreeMap.put(2000, "ALTERED_VALUE_2000"));
            assertEquals("ALTERED_VALUE_2000", myTreeMap.get(2000));

            assertEquals("VALUE_1000", myTreeMap.put(1000, "ALTERED_VALUE_1000"));
            assertEquals("ALTERED_VALUE_1000", myTreeMap.get(1000));

            assertEquals(19, myTreeMap.size());
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

            assertEquals(11, myTreeMap.size());

            assertNull(myTreeMap.get(1000));
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

            assertNull(myTreeMap.remove(2));
            assertNull(myTreeMap.remove(1000));

            assertEquals(11, myTreeMap.size());
        }
    }

    @Nested
    class forEach {

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

        List<Map<Integer, String>> expectedMapList = new ArrayList<>();
        {
            expectedMapList.add(Map.of(0, get(0)));
            expectedMapList.add(Map.of(1, get(1)));
            expectedMapList.add(Map.of(2, get(2)));
            expectedMapList.add(Map.of(9, get(9)));
            expectedMapList.add(Map.of(250, get(250)));
            expectedMapList.add(Map.of(260, get(260)));
            expectedMapList.add(Map.of(270, get(270)));
            expectedMapList.add(Map.of(300, get(300)));
            expectedMapList.add(Map.of(500, get(500)));
            expectedMapList.add(Map.of(1000, get(1000)));
            expectedMapList.add(Map.of(1250, get(1250)));
            expectedMapList.add(Map.of(1500, get(1500)));
            expectedMapList.add(Map.of(1700, get(1700)));
            expectedMapList.add(Map.of( 1800, get(1800)));
            expectedMapList.add(Map.of(1850, get(1850)));
            expectedMapList.add(Map.of(1900, get(1900)));
            expectedMapList.add(Map.of(1950, get(1950)));
            expectedMapList.add(Map.of(2000, get(2000)));
            expectedMapList.add(Map.of(3000, get(3000)));
        }

        List<Map<Integer, String>> list = new ArrayList<>();

        @Test
        void testForEachMethod() {
            myTreeMap.forEach((k, v) -> list.add(Map.of(k, v)));
            assertEquals(expectedMapList, list);
        }
    }

    @Nested
    class size {
        // The size() method is tested in other tests
    }


    private String get(int key) {
        return myTreeMap.get(key);
    }

    private void put(int key) {
        myTreeMap.put(key, "VALUE_" + key);
    }
}
