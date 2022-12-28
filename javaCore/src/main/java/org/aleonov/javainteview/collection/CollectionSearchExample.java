package org.aleonov.javainteview.collection;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class CollectionSearchExample {
    private static Integer bigCount = 1_000_000;
    private static Integer count = 100_000;

    public static void main(String[] args) {
        var arrList = IntStream
                .iterate(0, operand -> operand + 1)
                .boxed()
                .limit(count)
                .toList();

        var linkedList = new LinkedList<>(arrList);
        var set = new HashSet<>(arrList);

        // O(1) 5-8 ms
        meterArrayList(arrList);
        // на 1 млн записей O(n) ~ очень долго 311212 ms
        meterArrayList(linkedList);
        // на 1 млн записей
        meterSet(set);
    }

    public static void meterArrayList(List<Integer> list) {
        var start = System.currentTimeMillis();
        for (int i = 0; i < list.size(); i++) {
            var element = list.get(i);
        }
        var end = System.currentTimeMillis();
        System.out.println("["+ list.getClass().getName() + "] " +"Execution time:" + (end - start));
        System.out.println();
    }

    public static void meterSet(Set<Integer> set) {
        var start = System.currentTimeMillis();
        for (int i = 0; i < set.size(); i++) {
            var element = set.contains(i);
        }
        var end = System.currentTimeMillis();
        System.out.println("["+ set.getClass().getName() + "] " +"Execution time:" + (end - start));
    }
}
