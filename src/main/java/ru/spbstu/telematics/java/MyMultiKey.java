package ru.spbstu.telematics.java;

import java.io.Serializable;
import java.util.Arrays;

public class MyMultiKey<K> implements Serializable {
    private static final long serialVersionUID = 1L; // Для сериализации
    private final K[] keys; 
    private transient int hashCode;

    public MyMultiKey(K[] keys) {
        this(keys, true); 
    }

    // Конструктор, принимающий массив ключей с опцией клонирования
    public MyMultiKey(K[] keys, boolean makeClone) {
        if (keys == null) {
            throw new NullPointerException("The array of keys must not be null");
        }
        this.keys = makeClone ? keys.clone() : keys; 
        this.hashCode = Arrays.hashCode(this.keys); 
    }

    public MyMultiKey(K key1, K key2) {
        this((K[]) new Object[]{key1, key2}, false); 
    }

    public MyMultiKey(K key1, K key2, K key3) {
        this((K[]) new Object[]{key1, key2, key3}, false); 
    }

    public MyMultiKey(K key1, K key2, K key3, K key4) {
        this((K[]) new Object[]{key1, key2, key3, key4}, false); 
    }

    public MyMultiKey(K key1, K key2, K key3, K key4, K key5) {
        this((K[]) new Object[]{key1, key2, key3, key4, key5}, false); 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MyMultiKey<?> other = (MyMultiKey<?>) obj;
        if (keys.length != other.keys.length) {
            return false;
        }
        for (int i = 0; i < keys.length; i++) {
            if (!keys[i].equals(other.keys[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        return "MultiKey" +  Arrays.toString(keys);
    }

    protected Object readResolve() {
        this.hashCode = Arrays.hashCode(this.keys);
        return this;
    }

    public K getKey(int index) {
        if (index < 0 || index >= keys.length) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return keys[index];
    }

    public K[] getKeys() {
        return keys.clone();
    }

    public int size() {
        return keys.length;
    }
}