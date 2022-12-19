package leetCode;

import java.util.concurrent.Semaphore;

public class N_1115_PrintFooBarAlternately {
    public static void main(String[] args) throws InterruptedException {
        var fooBar = new FooBar(4);
        var thread1 = new Thread(() -> {
            try {
                fooBar.foo(() -> System.out.println("foo"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        var thread2 = new Thread(() -> {
            try {
                fooBar.bar(() -> System.out.println("bar"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

        Thread.sleep(5000);

    }

    private static class FooBar {
        private int n;
        Semaphore semaphoreFoo, semaphoreBar;

        private Object lock = new Object();

        public FooBar(int n) {
            this.n = n;

            semaphoreFoo = new Semaphore(1);
            semaphoreBar = new Semaphore(0);
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                semaphoreFoo.acquire();

                printFoo.run();
                semaphoreBar.release();
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                semaphoreBar.release();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                semaphoreBar.acquire();
            }
        }
    }
}
