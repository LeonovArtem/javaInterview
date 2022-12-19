package codeExample.Java_Stream_API.testExample;

import java.util.List;
import java.util.stream.Stream;

public class FlatMapExample {
    public static void main(String[] args) {
        var list1 = List.of(1, 2, 3);
        var list2 = List.of(4, 5, 6);
        List<Integer> list = Stream.of(list1, list2)
                .peek(value -> System.out.println(value) )
                .flatMap(value -> value.stream())
                .toList();

        System.out.println(list);
    }
}
