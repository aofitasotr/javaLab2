package ru.spbstu.telematics.java;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static junit.framework.Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(AppTest.class);
        return suite;
    }

    @Test
    public void testConstructorWithArray() {
        String[] keys = {"key1", "key2"};
        MyMultiKey<String> myMultiKey = new MyMultiKey<>(keys);
        MultiKey<String> apacheMultiKey = new MultiKey<>(keys);

        assertArrayEquals(keys, myMultiKey.getKeys());
        assertArrayEquals(keys, apacheMultiKey.getKeys());
    }

    @Test
    public void testConstructorWithVarargs() {
        MyMultiKey<String> myMultiKey = new MyMultiKey<>("key1", "key2", "key3");
        MultiKey<String> apacheMultiKey = new MultiKey<>("key1", "key2", "key3");

        assertEquals(3, myMultiKey.size());
        assertEquals(3, apacheMultiKey.size());

        assertEquals(apacheMultiKey.getKey(0), myMultiKey.getKey(0));
        assertEquals(apacheMultiKey.getKey(1), myMultiKey.getKey(1));
        assertEquals(apacheMultiKey.getKey(2), myMultiKey.getKey(2));

    }


    @Test
    public void testEquals() {
        MyMultiKey<String> myKey1 = new MyMultiKey<>("key1", "key2");
        MyMultiKey<String> myKey2 = new MyMultiKey<>("key1", "key2");
        MyMultiKey<String> myKey3 = new MyMultiKey<>("key2", "key1");

        MultiKey<String> apacheKey1 = new MultiKey<>("key1", "key2");
        MultiKey<String> apacheKey2 = new MultiKey<>("key1", "key2");
        MultiKey<String> apacheKey3 = new MultiKey<>("key2", "key1");

        assertTrue(myKey1.equals(myKey2));
        assertFalse(myKey1.equals(myKey3));

        assertTrue(apacheKey1.equals(apacheKey2));
        assertFalse(apacheKey1.equals(apacheKey3));
    }

    @Test
    public void testHashCode() {
        MyMultiKey<String> key1 = new MyMultiKey<>("key1", "key2");
        MyMultiKey<String> key2 = new MyMultiKey<>("key1", "key2");
        
        assertEquals(key1.hashCode(), key2.hashCode()); // Хеш-коды должны совпадать
    }

    @Test
    public void testToString() {
        MyMultiKey<String> myMultiKey = new MyMultiKey<>("key1", "key2");
        MultiKey<String> apacheMultiKey = new MultiKey<>("key1", "key2");
        assertEquals(apacheMultiKey.toString(), myMultiKey.toString());
    }

    @Test
    public void testGetKeysClone() {
        MyMultiKey<String> myMultiKey = new MyMultiKey<>("key1", "key2");
        MultiKey<String> apacheMultiKey = new MultiKey<>("key1", "key2");

        Object[] myKeys = myMultiKey.getKeys();
        Object[] apacheKeys = apacheMultiKey.getKeys();

        myKeys[0] = "modified"; // Изменяем клонированный массив
        apacheKeys[0] = "modified";

        assertNotEquals("modified", myMultiKey.getKey(0)); // Оригинальный объект не должен измениться
        assertNotEquals("modified", apacheMultiKey.getKey(0));
    }

    @Test
    public void testHashMapPutAndGet() {
        Map<MyMultiKey<String>, String> myMap = new HashMap<>();
        Map<MultiKey<String>, String> apacheMap = new HashMap<>();

        MyMultiKey<String> myKey = new MyMultiKey<>("key1", "key2");
        MultiKey<String> apacheKey = new MultiKey<>("key1", "key2");

        myMap.put(myKey, "value1");
        apacheMap.put(apacheKey, "value1");

        assertEquals(myMap.get(myKey), apacheMap.get(apacheKey));
    }

    @Test
    public void testHashMapReplace() {
        Map<MyMultiKey<String>, String> myMap = new HashMap<>();
        Map<MultiKey<String>, String> apacheMap = new HashMap<>();

        MyMultiKey<String> myKey = new MyMultiKey<>("key1", "key2");
        MultiKey<String> apacheKey = new MultiKey<>("key1", "key2");

        myMap.put(myKey, "value1");
        apacheMap.put(apacheKey, "value1");

        myMap.put(myKey, "value2");
        apacheMap.put(apacheKey, "value2");

        assertEquals( myMap.get(myKey), apacheMap.get(apacheKey));
    }

    @Test
    public void testHashMapRemove() {
        Map<MyMultiKey<String>, String> myMap = new HashMap<>();
        Map<MultiKey<String>, String> apacheMap = new HashMap<>();

        MyMultiKey<String> myKey = new MyMultiKey<>("key1", "key2");
        MultiKey<String> apacheKey = new MultiKey<>("key1", "key2");

        myMap.put(myKey, "value1");
        apacheMap.put(apacheKey, "value1");

        myMap.remove(myKey);
        apacheMap.remove(apacheKey);

        assertNull(myMap.get(myKey));
        assertNull(apacheMap.get(apacheKey));
    }

}
