package org.aleonov.javainteview.collection;

import java.util.HashMap;

// Почему нельзя использовать byte[] в качестве ключа в HashMap?
public class HashMapWithByteArr {
    public static void main(String[] args) {
        /**
         * хеш-код массива не зависит от хранимых в нем элементов, а присваивается при создании массива:
         * Метод вычисления хеш-кода массива не переопределен
         * и вычисляется по-стандартному Object.hashCode() на основании адреса массива.
         */
        HashMap<byte[], Integer> map = new HashMap<>();
        byte[] arr1 = new byte[] {'a', 'b'};
        byte[] arr2 = new byte[] {'c', 'd'};
        byte[] arr3 = new byte[] {'a', 'b'};
        map.put(arr1, 1);
        map.put(arr2, 2);
        map.put(arr3, 3);

        // Все элементы добавились - хотя arr1 == arr3 !
        System.out.println(map);

        /**
         * Так же у массивов не переопределен equals и выполняет сравнение указателей.
         * Это приводит к тому, что обратится к сохраненному с ключом-массивом элементу
         * не получится при использовании другого массива такого же размера и с такими же элементами,
         * доступ можно осуществить лишь в одном случае — при использовании той же самой ссылки на массив,
         * что использовалась для сохранения элемента.
         */
        System.out.println("Arr1: " + map.get(arr1));
        System.out.println("Arr2: " + map.get(arr2));
        System.out.println("Get by byte[]: " + map.get(new byte[] {'a', 'b'}));
    }
}
