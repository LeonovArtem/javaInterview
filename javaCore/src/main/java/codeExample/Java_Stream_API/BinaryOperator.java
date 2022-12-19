package codeExample.Java_Stream_API;

import java.util.stream.Stream;

/**
 * BinaryOperator<T> принимает в качестве параметра два объекта типа T,
 * выполняет над ними бинарную операцию и возвращает ее результат также в виде объекта типа T.
 */
public class BinaryOperator {
    public static void main(String[] args) {
        var sum = Stream
                .iterate(1, integer -> integer + 1)
                .limit(3)
                .reduce((integer, integer2) -> integer + integer2)
                .get();

        System.out.println(sum);
    }
}
