package org.aleonov.javainteview.streamAPI;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Predicate<T> - проверяет соблюдение некоторого условия. Возвращает true или false.
 * Пример: .filter(n -> n % 2 == 0)
 */
public class Predicate {
    public static void main(String[] args) {
        var allList = new ArrayList<>();
        var list = Stream
                .iterate(1, integer -> integer + 1)
                .peek(System.out::println)
                .filter(
                        new java.util.function.Predicate<Integer>() {
                            @Override
                            public boolean test(Integer n) {
                                return n % 2 == 0;
                            }
                        }
                )
//                .peek(System.out::println)
                .limit(5)
                .toList();

        System.out.println(list);
    }
}
