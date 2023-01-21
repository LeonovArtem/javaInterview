package org.aleonov.javainteview.multithreiding.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolEx1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            int numTask = i;
            executorService.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                var name = Thread.currentThread().getName();
                System.out.println(name + " :Task n: " + numTask + " is done");
            });
        }

        System.out.println("Main is down");
    }
}
