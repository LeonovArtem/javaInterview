package org.aleonov.javainteview.multithreiding.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CashedThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService scheduledThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            scheduledThreadPool.execute(new MyTask(i));
        }

        System.out.println("Main is down");
    }

    private static class MyTask implements Runnable {
        private final Integer numTask;

        public MyTask(Integer numTask) {
            this.numTask = numTask;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            var name = Thread.currentThread().getName();
            System.out.println(name + " :Task " + numTask + " is done");
        }
    }

}
