package ru.spbstu.telematics.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

public class App 
{
    public static void main( String[] args )
    {
        MyArrayDeque<Integer> deque = new MyArrayDeque<>();

        // Добавляем элементы в конец
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        System.out.println("Deque после добавления элементов: " + Arrays.toString(deque.toArray()));

        // Добавляем элементы в начало
        deque.addFirst(0);
        System.out.println("Deque после добавления элемента в начало: " + Arrays.toString(deque.toArray()));

        // Получаем первый и последний элементы
        System.out.println("Первый элемент: " + deque.getFirst());
        System.out.println("Последний элемент: " + deque.getLast());

        // Удаляем элементы
        deque.removeFirst();
        System.out.println("Deque после удаления первого элемента: " + Arrays.toString(deque.toArray()));

        deque.removeLast();
        System.out.println("Deque после удаления последнего элемента: " + Arrays.toString(deque.toArray()));

        // Проверяем, содержит ли Deque определенные элементы
        System.out.println("Содержит ли 2? " + deque.contains(2));
        System.out.println("Содержит ли 3? " + deque.contains(3));

        // Проверяем размер Deque
        System.out.println("Размер Deque: " + deque.size());

        // Очищаем Deque
        deque.clear();
        System.out.println("Deque после очистки: " + Arrays.toString(deque.toArray()));
        System.out.println("Размер Deque после очистки: " + deque.size());

        for (int i = 0; i < 10; i++) {
            deque.addLast(i);
        }
        System.out.println("Исходный Deque: " + Arrays.toString(deque.toArray()));

        // Удаляем четные числа
        Predicate<Integer> isEven = number -> number % 2 == 0;
        deque.removeIf(isEven);
        System.out.println("Deque после удаления четных чисел: " + Arrays.toString(deque.toArray()));

        // Создаем коллекцию для сохранения
        Collection<Integer> toRetain = new ArrayList<>();
        toRetain.add(1);
        toRetain.add(3);

        // Сохраняем только элементы, которые есть в коллекции
        deque.retainAll(toRetain);
        System.out.println("Deque после retainAll: " + Arrays.toString(deque.toArray()));
    
    }

    
}
