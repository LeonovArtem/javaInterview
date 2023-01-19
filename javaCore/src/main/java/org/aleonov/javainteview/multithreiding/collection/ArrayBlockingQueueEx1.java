package org.aleonov.javainteview.multithreiding.collection;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueEx1 {
    public static void main(String[] args) {
        var queue = new ArrayBlockingQueue<>(4);

        var producer = new Thread(() -> {
            var i = 0;
            while (true) {
                ++i;
                try {
                    queue.put(i);
                    System.out.println("produce: " + i + queue);
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        var consumer = new Thread(() -> {
            while (true) {
                try {
                    var element = queue.take();
                    System.out.println("Consumer: " + element);
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
