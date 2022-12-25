package org.aleonov.javainteview.multithreiding;

public class WaitAndNotify {
    public static void main(String[] args) throws RuntimeException, InterruptedException {
        var worker = new Worker();
        var producerThread = new Thread(() -> {
            try {
                worker.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        var consumerThread = new Thread(() -> {
            try {
                worker.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producerThread.start();
        consumerThread.start();

        Thread.sleep(2000);

    }
}

class Worker {
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ":" + "Start produce...");
            lock.wait(1000);
            System.out.println("Finish produce...");
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + ":" + "Start consume...");
            Thread.sleep(6000);
            System.out.println("Notify...");
            lock.notifyAll();
        }
    }
}
