package codeExample.Java_Stream_API;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Supplier<T> - не принимает никаких аргументов, но должен возвращать объект типа T.
 */
public class SupplierEx {
    public static void main(String[] args) {
        var randomGenerator = new Random();

        Stream
                .generate(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        return randomGenerator.nextInt(10);
                    }
                })
                .limit(5)
                .forEach(System.out::println);
    }
}
