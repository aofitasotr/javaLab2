package ru.spbstu.telematics.java;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MyArrayDeque<E> implements Iterable<E>, Deque<E> {
    private Object[] elements;
    private int size;

    public MyArrayDeque() {
        elements = new Object[16];
        size = 0;
    }

    public MyArrayDeque(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be non-negative");
        }
        elements = new Object[capacity];
        size = 0;
    }

    public MyArrayDeque(Collection<? extends E> col) {
        if (col == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        size = col.size();
        elements = new Object[size];
        int index = 0;
        for (E element : col) {
            elements[index++] = element;
        }
    }

    private void resize() {
        Object[] newArray = new Object[elements.length * 2 + 1];
        System.arraycopy(elements, 0, newArray, 0, elements.length);
        elements = newArray;
    }

    @Override
    public int size() {
        return size;
    }
    

    @Override
    public Iterator<E> iterator() {
        return new MyIterator(1); // 1 для прямого итератора
    }

    
    @Override
    public Iterator<E> descendingIterator() {
        return new MyIterator(-1); // -1 для обратного итератора
    }
    
    @Override
    public boolean add(E e) {
        if (e == null) {
            throw new NullPointerException("Element cannot be null");
        }
        addLast(e);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        boolean isChanged = false;
        for (E element : c) {
            if (element == null) {
                throw new NullPointerException("Element in collection cannot be null");
            }
            addLast(element); 
            isChanged = true;
        }
        return isChanged; 
    }

    @Override
    public void addFirst(E element) {

        if (element == null) throw new NullPointerException();
        if (size == elements.length) {
            resize();
        }
        for (int i = size; i > 0; i--) {
            elements[i] = elements[i - 1];
        }
        elements[0] = element; 
        size++;
    }

    @Override
    public void addLast(E element) {

        if (element == null) throw new NullPointerException();
        if (size == elements.length) {
            resize(); 
        }
        elements[size++] = element; 
    }
    
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null; 
        }
        elements = new Object[16];
        size = 0;
    }

    @Override
    public MyArrayDeque<E> clone() {
        MyArrayDeque<E> clonedDeque = new MyArrayDeque<>(size); 
        System.arraycopy(elements, 0, clonedDeque.elements, 0, size);
        clonedDeque.size = this.size; 
        return clonedDeque; 
    } 
    
    @Override
    public boolean contains(Object o) {
        if (o == null) {
            return false;
        } 
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                return true;
            }
        }
        return false; 
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0; 
    }

    @Override
    public E element() {
        return peekFirst();
    }
    
    @Override
    public E getFirst() {
        return peekFirst(); 
    }
    
    @Override
    public E getLast() {
        return peekLast();
    }

    @Override
    public E peek() {
        return peekFirst();
    }

    @Override
    public E peekFirst() {
        if (isEmpty()) {
            return null;
        }
        return (E) elements[0]; 
    }
    
    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return (E) elements[size - 1]; 
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        if (action == null) {
            throw new NullPointerException("The specified action cannot be null");
        }
        for (int i = 0; i < size; i++) {
            action.accept((E) elements[i]);
        }
    }

    @Override
    public boolean offer(E e) {
        addLast(e); 
        return true; 
    }

    @Override
    public boolean offerFirst(E e) {
        if (e == null) {
            throw new NullPointerException("Element cannot be null");
        }
        addFirst(e); 
        return true;
    }
    
    @Override
    public boolean offerLast(E e) {
        if (e == null) {
            throw new NullPointerException("Element cannot be null");
        }
        addLast(e); 
        return true;
    }

    @Override
    public E poll() {
        return pollFirst();
    }
 
    @Override
    public E pollFirst() {
        if (isEmpty()) {
            return null;
        }
        E firstElement = (E) elements[0];
        for (int i = 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        elements[--size] = null;
        return firstElement; 
    }

    @Override
public boolean equals(Object obj) {
    // Проверка на ссылочное равенство
    if (this == obj) {
        return true;
    }
    // Проверка на тип
    if (!(obj instanceof MyArrayDeque)) {
        return false;
    }
    MyArrayDeque<?> other = (MyArrayDeque<?>) obj;
    
    // Сравнение размеров
    if (this.size != other.size) {
        return false;
    }
    
    // Сравнение элементов
    for (int i = 0; i < this.size; i++) {
        // Проверка на равенство элементов
        if (!elements[i].equals(other.elements[i])) {
            return false;
        }
    }
    
    return true; // Все проверки пройдены, дека равны
}

    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        E lastElement = (E) elements[size - 1];
        elements[--size] = null;
        return lastElement;
    }

    @Override
    public E remove() {
        return removeFirst();
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty"); 
        }
        return pollFirst();
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty"); 
        }
        return pollLast();
    }

    @Override
    public E pop() {
        return removeFirst(); 
    }

    @Override
    public void push(E e) {
        addFirst(e); 
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        boolean modified = false;
        for (Object element : c) {
            while (remove(element)) {
                modified = true; 
            }
        }
        return modified;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        if (o == null) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[size - 1] = null; 
                size--;
                return true;
            }
        }
        return false; 
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        if (o == null) {
            return false;
        }
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(elements[i])) {
               
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[size - 1] = null; 
                size--;
                return true;
            }
        }
        return false; 
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        if (filter == null) {
            throw new NullPointerException("Filter cannot be null");
        }
        boolean modified = false;
        for (int i = 0; i < size; ) {
            if (filter.test((E) elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[size - 1] = null; 
                size--;
                modified = true; 
            } else {
                i++; 
            }
        }
        return modified;

    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            throw new NullPointerException("Collection cannot be null");
        }
        
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!c.contains(elements[i])) {
                System.arraycopy(elements, i + 1, elements, i, size - i - 1);
                elements[size - 1] = null; 
                size--;
                modified = true;
                i--;
            }
        }
        return modified;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];  
        for (int i = 0; i < size; i++) {
            array[i] = elements[i]; 
        }
        return array; 
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a == null) {
            throw new NullPointerException("The specified array cannot be null");
        }

        Class<?> elementClass = a.getClass().getComponentType();
        if (!elementClass.isInstance(a[size])) {
            throw new ArrayStoreException();
        }

        if (a.length < size) {
            return (T[]) java.util.Arrays.copyOf(elements, size, a.getClass());
        }

        System.arraycopy(elements, 0, a, 0, size);
        // Устанавливаем следующий элемент в null, если есть лишнее место
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }
   
    private class MyIterator implements Iterator<E> {
        private int currentIndex;
        private final int direction; // 1 для прямого, -1 для обратного
        private boolean canRemove = false;

        public MyIterator(int direction) {
            this.direction = direction;
            this.currentIndex = (direction == 1) ? 0 : size - 1; 
        }

        @Override
        public boolean hasNext() {
            return (direction == 1) ? currentIndex < size : currentIndex >= 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate");
            }
            canRemove = true;
            E element = (E) elements[currentIndex];
            currentIndex += direction;
            return element;
        }

        @Override
        public void remove() {
            if (!canRemove) {
                throw new IllegalStateException("Cannot remove element before calling next() or after removing an element");
            }
            
            if (direction == 1) { // Прямой итератор
                for (int i = currentIndex - 1; i < size - 1; i++) {
                    elements[i] = elements[i + 1];
                } 
                currentIndex--;
            } else { // Обратный итератор
                for (int i = currentIndex + 1; i < size; i++) {
                    elements[i - 1] = elements[i];
                } 
            }
            elements[--size] = null; 
            canRemove = false; 
        }
    }

}