package org.aleonov.javainteview.multithreiding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread pool - пул потоков, позволяет выполнять задания в N потоках.
 */
public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Task(i + 1));
        }

        executorService.shutdown();

    }

    static class Task implements Runnable {
        private final int id;

        Task(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.printf("The task with id %d executed \n", id);
        }
    }
}
