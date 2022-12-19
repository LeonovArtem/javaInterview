package codeExample.Java_Stream_API;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Consumer<T> выполняет некоторое действие над объектом типа T, при этом ничего не возвращая.
 */
public class ConsumerEx {
    public static void main(String[] args) {
        Stream
                .iterate(1, integer -> integer + 1)
                .limit(5)
                .forEach(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        System.out.println(integer);
                    }
                });
    }
}
