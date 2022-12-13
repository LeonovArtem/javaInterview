package codeExample.Java_Stream_API;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;

public class СемействаФункциональныхИнтерфейсов {
    public static void main(String[] args) {
        Runnable messagePrinter = () -> System.out.println("It is function!");
        messagePrinter.run();

        var thread1 = new Thread(messagePrinter);
        thread1.start();

        Consumer<Integer> add = (x) -> System.out.println("|" + x + "|");
        BinaryOperator<Long> sum = (x, y) -> x + y;
    }

}

@FunctionalInterface
interface ManualPrinter {
    void print();
}
