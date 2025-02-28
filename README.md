# Класс MyMultiKey

## Обзор

Класс `MyMultiKey` является обобщенной реализацией структуры данных с несколькими ключами на языке Java. Он позволяет создавать объект, который может содержать несколько ключей одного типа, предоставляя методы для сравнения на равенство, генерации хэш-кода и извлечения ключей. Этот класс поддерживает сериализацию, что делает его подходящим для использования в распределенных приложениях или для сохранения состояния объектов.

## Особенности

- **Несколько ключей**: Поддерживает произвольное количество ключей.
- **Сериализация**: Реализует интерфейс `Serializable` для легкой сериализации объектов.
- **Сравнение и хэширование**: Переопределяет методы `equals()` и `hashCode()`, чтобы обеспечить корректное поведение в коллекциях.
- **Доступ к ключам**: Предоставляет методы для извлечения ключей и их количества.

## Использование

### Конструктор

Вы можете создать экземпляр `MyMultiKey`, используя один из следующих конструкторов:

1. **Конструктор с массивом**: 
   ```java
   MyMultiKey<K> multiKey = new MyMultiKey<>(new K[]{key1, key2, ...});
   ```

2. **Опция клонирования**: 
   ```java
   MyMultiKey<K> multiKey = new MyMultiKey<>(new K[]{key1, key2, ...}, true); // Клонирует массив
   ```

3. **Конструкторы с несколькими ключами**: 
   ```java
   MyMultiKey<K> multiKey2 = new MyMultiKey<>(key1, key2);
   MyMultiKey<K> multiKey3 = new MyMultiKey<>(key1, key2, key3);
   MyMultiKey<K> multiKey4 = new MyMultiKey<>(key1, key2, key3, key4);
   MyMultiKey<K> multiKey5 = new MyMultiKey<>(key1, key2, key3, key4, key5);
   ```

### Методы

- **`equals(Object obj)`**: Сравнивает этот объект с другим на равенство.
- **`hashCode()`**: Возвращает хэш-код для этого объекта.
- **`toString()`**: Возвращает строковое представление много-ключа.
- **`getKey(int index)`**: Извлекает ключ по указанному индексу.
- **`getKeys()`**: Возвращает клон массива ключей.
- **`size()`**: Возвращает количество ключей в много-ключе.

### Пример

```java
MyMultiKey<String> multiKey = new MyMultiKey<>(new String[]{"key1", "key2", "key3"});
System.out.println(multiKey); // Вывод: MultiKey[key1, key2, key3]
System.out.println(multiKey.size()); // Вывод: 3
```
