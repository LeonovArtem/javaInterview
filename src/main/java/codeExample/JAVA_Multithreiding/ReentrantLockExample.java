package codeExample.JAVA_Multithreiding;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    public static void main(String[] args) throws InterruptedException {
        final Runner runner = new Runner();
        var thread1 = new Thread(runner::firstThread);
        var thread2 = new Thread(runner::secondThread);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(runner.getI());
    }

    public static class Runner {
        private int i;

        Lock lock = new ReentrantLock();

        public void increase() {
            lock.lock();
            for (int i = 0; i < 10000; i++) {
                this.i++;
            }
            lock.unlock();
        }

        public void firstThread() {
            this.increase();
        }

        public void secondThread() {
            this.increase();
        }

        public int getI() {
            return i;
        }
    }
}
