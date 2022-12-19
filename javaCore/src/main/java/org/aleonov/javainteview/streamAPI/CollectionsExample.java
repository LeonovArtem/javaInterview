package org.aleonov.javainteview.streamAPI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionsExample {
    public static void main(String[] args) {
        List<String> list = List.of("a2", "a1", "c", "d");
        list.stream()
                .peek(s -> System.out.println("peak: " + s))
                .forEach(System.out::println);

        Set<String> set = new HashSet<>(list);

        System.out.println(set);
    }
}
