package codeExample.Java_Stream_API;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Function<T,R> - представляет функцию перехода от объекта типа T к объекту типа R.
 */
public class FunctionEx {
    public static void main(String[] args) {
        Stream
                .iterate(1, integer -> integer + 1)
                .limit(5)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        return "|" + integer + "|";
                    }
                })
                .forEach(System.out::println);
    }
}
