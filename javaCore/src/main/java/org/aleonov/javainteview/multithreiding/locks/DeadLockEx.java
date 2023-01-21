package org.aleonov.javainteview.multithreiding.locks;

public class DeadLockEx {
    public static void main(String[] args) throws InterruptedException {
        Object lock1 = new Object();
        Object lock2 = new Object();
        var thread1 = new Thread(() -> Runner.run(lock1, lock2));
        var thread2 = new Thread(() -> Runner.run(lock2, lock1));
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("Main done");
    }

    private class Runner {
        public static void run(Object ob1, Object ob2) {
            System.out.println("Попытка хахватить монитор объекта 1");
            synchronized (ob1) {
                System.out.println("монитор объекта 1 захвачен");
                synchronized (ob2) {
                    System.out.println("Run..");
                }
            }
        }
    }
}
