package org.aleonov.javainteview.multithreiding.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < 4; i++) {
            scheduledThreadPool.schedule(new MyTask(i), 3, TimeUnit.SECONDS);
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
