package org.aleonov.javainteview.streamAPI;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * UnaryOperator - аналогичен BinaryOperator, но принимает в качестве аргумента только один параметр.
 */
public class UnaryOperatorEx {
    public static void main(String[] args) {
        Stream
                .iterate(1, new UnaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer integer) {
                        return integer + 1;
                    }
                })
                .limit(5)
                .forEach(System.out::println);
    }
}
