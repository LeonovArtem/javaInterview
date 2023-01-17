package org.aleonov.javainteview.multithreiding;

import java.util.concurrent.TimeUnit;

public class InteraptThreadExample {
    public static void main(String[] args) throws InterruptedException {
//        firstExample();

        Runnable task = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("is do something...");
            }

            System.out.println("Is interrupted!");
        };

        var thread = new Thread(task);
        thread.start();

        TimeUnit.SECONDS.sleep(3000);
        thread.interrupt();
    }

    private static void firstExample() throws InterruptedException {
        Runnable task = () -> {
            try {
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        };
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("Main sleep");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Main wake up");

        thread.interrupt();

        System.out.println("Main done!");
    }
}
