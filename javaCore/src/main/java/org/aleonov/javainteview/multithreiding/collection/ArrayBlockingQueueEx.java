package org.aleonov.javainteview.multithreiding.collection;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueEx {
    public static void main(String[] args) {
        var queue = new ArrayBlockingQueue<>(4);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);

        // IllegalStateException: Queue full
        queue.offer(5);

        System.out.println(queue);
    }
}
