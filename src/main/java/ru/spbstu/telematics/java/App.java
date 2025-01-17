package ru.spbstu.telematics.java;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.keyvalue.MultiKey;

public class App 
{
    public static void main( String[] args )
    {
        MyMultiKey<Object> key1 = new MyMultiKey<>(new Object[]{"key1", 123, 45.67});
        MyMultiKey<Object> key2 = new MyMultiKey<>("ключ 1", 123, 45.67);
        MyMultiKey<Object> key3 = new MyMultiKey<>("первый", 0, 45.67, true);
        MyMultiKey<Object> key4 = new MyMultiKey<>("", -123, 45.67, false, 'A');

        System.out.println(key1);
        System.out.println(key2); 
        System.out.println(key3); 
        System.out.println(key4); 

        String[] keys1 = {"key1", "key2", "key3"};
        String[] keys2 = {"ключ1", "key2", "key3"};
        
        MyMultiKey<String> k1 = new MyMultiKey<>(keys1, false);
        MyMultiKey<String> k2 = new MyMultiKey<>(keys1, true);
        MyMultiKey<String> k3 = new MyMultiKey<>(keys2, false);
        System.out.println(k1.equals(k2));
        System.out.println(k1.equals(k3));
        keys2[0] = "key1";
        System.out.println(k1.equals(k3));
        System.out.println(k3);
        keys2[0] = "ключ1";
        
        System.out.println(key1.size()); 
        System.out.println(key4.size()); 

        System.out.println(key2.getKey(0));
        System.out.println(key1.getKey(1)); 
        System.out.println(key3.getKey(2));
    }
}
