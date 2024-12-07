package ru.spbstu.telematics.java;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.NoSuchElementException;

import junit.framework.Test;
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
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testAddFirst() {
        Deque<Integer> javaDeque = new ArrayDeque<>();
        Deque<Integer> myDeque = new MyArrayDeque<>();

        javaDeque.addFirst(1);
        myDeque.addFirst(1);
        assertEquals(javaDeque.removeFirst(), myDeque.removeFirst());

        javaDeque.addFirst(2);
        myDeque.addFirst(2);

        javaDeque.addFirst(3);
        myDeque.addFirst(3);

        assertEquals(javaDeque.removeFirst(), myDeque.removeFirst());
        assertEquals(javaDeque.size(), myDeque.size());
        assertEquals(javaDeque.removeFirst(), myDeque.removeFirst());
        assertEquals(javaDeque.size(), myDeque.size());
        assertEquals(javaDeque.isEmpty(), myDeque.isEmpty());
    }

    public void testAddLast() {
        Deque<Integer> javaDeque = new ArrayDeque<>();
        Deque<Integer> myDeque = new MyArrayDeque<>();

        javaDeque.addLast(1);
        myDeque.addLast(1);
        assertEquals(javaDeque.removeLast(), myDeque.removeLast());

        javaDeque.addLast(2);
        myDeque.addLast(2);

        javaDeque.addLast(3);
        myDeque.addLast(3);

        assertEquals(javaDeque.size(), myDeque.size());
        assertEquals(javaDeque.removeLast(), myDeque.removeLast());
        assertEquals(javaDeque.removeLast(), myDeque.removeLast());
        assertEquals(javaDeque.size(), myDeque.size());
        assertEquals(javaDeque.isEmpty(), myDeque.isEmpty());
    }

    public void testRemoveLastThrowsException() {
    Deque<Integer> myDeque = new MyArrayDeque<>();

    // Проверяем, что исключение выбрасывается при попытке удалить элемент из пустого дека
    try {
        myDeque.removeLast();
        fail("Expected NoSuchElementException to be thrown");
    } catch (NoSuchElementException e) {
        assertEquals("Deque is empty", e.getMessage());
    }

    } 
    
    public void testContains() {
        Deque<Integer> javaDeque = new ArrayDeque<>();
        Deque<Integer> myDeque = new MyArrayDeque<>();
        javaDeque.addAll(List.of(1,2,3,4,5));
        myDeque.addAll(List.of(1,2,3,4,5));
        assertEquals(javaDeque.contains(3), myDeque.contains(3));
        assertEquals(javaDeque.contains(6), myDeque.contains(6));
    }

    public void testRemoveIf() {
        Deque<Integer> javaDeque = new ArrayDeque<>();
        Deque<Integer> myDeque = new MyArrayDeque<>();
        javaDeque.addAll(List.of(1,2,3,4,5,6));
        myDeque.addAll(List.of(1,2,3,4,5,6));
        Predicate<Integer> isEven = number -> number % 2 == 0;
        javaDeque.removeIf(isEven);
        myDeque.removeIf(isEven);
        assertArrayEquals(javaDeque.toArray(), myDeque.toArray());
    }

    public void testRetainAll() {
        Deque<Integer> javaDeque = new ArrayDeque<>();
        Deque<Integer> myDeque = new MyArrayDeque<>();
        javaDeque.addAll(List.of(1,2,3,4,5));
        myDeque.addAll(List.of(1,2,3,4,5));
        List<Integer> toRetain = List.of(1,3,5);
        javaDeque.retainAll(toRetain);
        myDeque.retainAll(toRetain);
        assertArrayEquals(javaDeque.toArray(), myDeque.toArray());
    }

    public void testClone() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>(Arrays.asList(1, 2, 3));
        MyArrayDeque<Integer> clonedDeque = myDeque.clone();

        // Проверяем, что клон - независимый объект
        clonedDeque.addFirst(0);
        assertNotSame(clonedDeque.toArray(), myDeque.toArray());
        assertEquals(4, clonedDeque.size());
        assertEquals(3, myDeque.size());
    }

    public void testPollMethods() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        assertNull(myDeque.pollFirst());
        assertNull(arrayDeque.poll());

        myDeque.addLast(1);
        arrayDeque.add(1);
        assertEquals(myDeque.pollFirst(), arrayDeque.poll());
        assertTrue(myDeque.isEmpty());
        assertTrue(arrayDeque.isEmpty());

        myDeque.addLast(2);
        myDeque.addLast(3);
        arrayDeque.add(2);
        arrayDeque.add(3);
        assertEquals(myDeque.pollFirst(), arrayDeque.poll());
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());

        assertEquals(myDeque.pollLast(), arrayDeque.poll());
        assertTrue(myDeque.isEmpty());
        assertTrue(arrayDeque.isEmpty());
    }

    public void testForEach() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        myDeque.addLast(1);
        myDeque.addLast(2);
        myDeque.addLast(3);
        arrayDeque.add(1);
        arrayDeque.add(2);
        arrayDeque.add(3);

        assertArrayEquals(arrayDeque.toArray(), myDeque.toArray());
    }

    public void testContainsAll() {
        Deque<Integer> myDeque = new MyArrayDeque<>(Arrays.asList(1, 2, 3));
        assertTrue(myDeque.containsAll(Arrays.asList(1, 3)));
        assertFalse(myDeque.containsAll(Arrays.asList(1, 4)));
    }

    public void testPeekMethods() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        assertNull(myDeque.peekFirst());
        assertNull(arrayDeque.peek());

        myDeque.addLast(1);
        arrayDeque.add(1);
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
        assertEquals(myDeque.peekLast(), arrayDeque.peek());

        myDeque.addLast(2);
        arrayDeque.add(2);
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
        assertEquals(myDeque.peekLast(), arrayDeque.peekLast());
    }

    public void testOffer() {
        // Проверка добавления одного элемента
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();
        assertTrue(myDeque.offer(1));
        arrayDeque.offer(1);
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
        assertEquals(myDeque.peekLast(), arrayDeque.peek());

        // Проверка добавления нескольких элементов
        assertTrue(myDeque.offer(2));
        arrayDeque.offer(2);
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
        assertEquals(myDeque.peekLast(), arrayDeque.peekLast());

        assertTrue(myDeque.offer(3));
        arrayDeque.offer(3);
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
        assertEquals(myDeque.peekLast(), arrayDeque.peekLast());
    }

    public void testOfferFirst() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        myDeque.offerFirst(1);
        arrayDeque.offerFirst(1);
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
        assertEquals(myDeque.peekLast(), arrayDeque.peek());

        myDeque.offerFirst(2);
        arrayDeque.offerFirst(2);
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
        assertEquals(myDeque.peekLast(), arrayDeque.peekLast());
    }

    public void testOfferLast() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        myDeque.offerLast(1);
        arrayDeque.offerLast(1);
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
        assertEquals(myDeque.peekLast(), arrayDeque.peekLast());

        myDeque.offerLast(2);
        arrayDeque.offerLast(2);
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
        assertEquals(myDeque.peekLast(), arrayDeque.peekLast());
    }

    public void testRemoveFirstOccurrence() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        myDeque.offer(1);
        myDeque.offer(2);
        myDeque.offer(3);
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        arrayDeque.offer(3);

        assertTrue(myDeque.removeFirstOccurrence(2));
        assertTrue(arrayDeque.remove(2));
        assertEquals(myDeque.size(), arrayDeque.size());
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
    }

    public void testRemoveLastOccurrence() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        myDeque.offer(1);
        myDeque.offer(2);
        myDeque.offer(3);
        myDeque.offer(2);
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        arrayDeque.offer(3);
        arrayDeque.offer(2);

        assertTrue(myDeque.removeLastOccurrence(2));
        assertTrue(arrayDeque.removeLastOccurrence(2));
        assertEquals(myDeque.size(), arrayDeque.size());
        assertEquals(myDeque.peekLast(), arrayDeque.peekLast());
    }

    public void testPush() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        myDeque.push(1);
        arrayDeque.push(1);
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
        assertEquals(myDeque.peekLast(), arrayDeque.peekLast());

        myDeque.push(2);
        arrayDeque.push(2);
        assertEquals(myDeque.peekFirst(), arrayDeque.peek());
        assertEquals(myDeque.peekLast(), arrayDeque.peekLast());
    }

    public void testPop() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        myDeque.push(1);
        myDeque.push(2);
        arrayDeque.push(1);
        arrayDeque.push(2);

        assertEquals(arrayDeque.pop(), myDeque.pop());
        assertEquals(myDeque.size(), arrayDeque.size());
    }

    public void testToArray() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        myDeque.offer(1);
        myDeque.offer(2);
        myDeque.offer(3);
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        arrayDeque.offer(3);

        Object[] myArray = myDeque.toArray();
        Object[] arrayArray = arrayDeque.toArray();

        assertArrayEquals(myArray, arrayArray);
        assertEquals(3, myArray.length);
    }

    public void testToArrayEmpty() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Object[] myArray = myDeque.toArray();
        assertEquals(0, myArray.length);
    }

    public void testIterator() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        myDeque.offer(1);
        myDeque.offer(2);
        myDeque.offer(3);
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        arrayDeque.offer(3);

        Iterator<Integer> myIterator = myDeque.iterator();
        Iterator<Integer> arrayIterator = arrayDeque.iterator();

        while (myIterator.hasNext() && arrayIterator.hasNext()) {
            assertEquals(myIterator.next(), arrayIterator.next());
        }
        assertFalse(myIterator.hasNext());
        assertFalse(arrayIterator.hasNext());
    }

    public void testDescendingIterator() {
        MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
        Deque<Integer> arrayDeque = new ArrayDeque<>();

        myDeque.offer(1);
        myDeque.offer(2);
        myDeque.offer(3);
        arrayDeque.offer(1);
        arrayDeque.offer(2);
        arrayDeque.offer(3);

        Iterator<Integer> myDescendingIterator = myDeque.descendingIterator();
        Iterator<Integer> arrayDescendingIterator = arrayDeque.descendingIterator();

        while (myDescendingIterator.hasNext() && arrayDescendingIterator.hasNext()) {
            assertEquals(myDescendingIterator.next(), arrayDescendingIterator.next());
        }
        assertFalse(myDescendingIterator.hasNext());
        assertFalse(arrayDescendingIterator.hasNext());
    }

    public void testDescendingIteratorEmpty() {
    ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
    Iterator<Integer> descendingIterator = arrayDeque.descendingIterator();
    assertFalse(descendingIterator.hasNext());
    
    MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
    Iterator<Integer> myDescendingIterator = myDeque.descendingIterator();
    assertFalse(myDescendingIterator.hasNext());
    }


public void testIteratorRemove() {
    ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
    arrayDeque.offer(1);
    arrayDeque.offer(2);
    arrayDeque.offer(3);

    Iterator<Integer> arrayIterator = arrayDeque.iterator();
    assertTrue(arrayIterator.hasNext());
    arrayIterator.next(); // Переходим к первому элементу
    arrayIterator.remove(); // Удаляем первый элемент

    assertEquals(2, arrayDeque.size());
    assertEquals(Integer.valueOf(2), arrayDeque.peekFirst());

    MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
    myDeque.offer(1);
    myDeque.offer(2);
    myDeque.offer(3);

    Iterator<Integer> myIterator = myDeque.iterator();
    assertTrue(myIterator.hasNext());
    myIterator.next(); // Переходим к первому элементу
    myIterator.remove(); // Удаляем первый элемент

    assertEquals(2, myDeque.size());
    assertEquals(Integer.valueOf(2), myDeque.peekFirst());
}


public void testDescendingIteratorRemove() {
    ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
    arrayDeque.offer(1);
    arrayDeque.offer(2);
    arrayDeque.offer(3);

    Iterator<Integer> arrayDescendingIterator = arrayDeque.descendingIterator();
    assertTrue(arrayDescendingIterator.hasNext());
    arrayDescendingIterator.next(); // Переходим к последнему элементу
    arrayDescendingIterator.remove(); // Удаляем последний элемент

    assertEquals(2, arrayDeque.size());

    MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
    myDeque.offer(1);
    myDeque.offer(2);
    myDeque.offer(3);

    Iterator<Integer> myDescendingIterator = myDeque.descendingIterator();
    assertTrue(myDescendingIterator.hasNext());
    myDescendingIterator.next(); // Переходим к последнему элементу
    myDescendingIterator.remove(); // Удаляем последний элемент

    assertEquals(2, myDeque.size());
}

public void testRemoveAll() {
    ArrayDeque<Integer> arrayDeque = new ArrayDeque<>(Arrays.asList(1, 2, 3, 4, 5));
    MyArrayDeque<Integer> myDeque = new MyArrayDeque<>(Arrays.asList(1, 2, 3, 4, 5));
    
    List<Integer> toRemove = Arrays.asList(2, 4);
    
    arrayDeque.removeAll(toRemove);
    myDeque.removeAll(toRemove);
    
    // Сравниваем объекты
    assertEquals(arrayDeque.size(), myDeque.size());
    assertTrue(arrayDeque.contains(1) == myDeque.contains(1));
    assertTrue(arrayDeque.contains(3) == myDeque.contains(3));
    assertTrue(arrayDeque.contains(5) == myDeque.contains(5));
    assertTrue(arrayDeque.contains(2) == myDeque.contains(2));
    assertFalse( myDeque.contains(2) == true);
    assertTrue(arrayDeque.contains(4) == myDeque.contains(4));
    assertTrue( myDeque.contains(4) == false);
    assertArrayEquals(arrayDeque.toArray(), myDeque.toArray());
}

public void testClear() {
    ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
    MyArrayDeque<Integer> myDeque = new MyArrayDeque<>();
    
    arrayDeque.offer(1);
    arrayDeque.offer(2);
    arrayDeque.offer(3);
    
    myDeque.offer(1);
    myDeque.offer(2);
    myDeque.offer(3);
    
    arrayDeque.clear();
    myDeque.clear();
    
    // Сравниваем объекты
    assertEquals(arrayDeque.size(), myDeque.size());
    assertTrue(arrayDeque.isEmpty() == myDeque.isEmpty());
}

}
