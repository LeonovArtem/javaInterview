package org.aleonov.javainteview.patterns;

public class SingletonExample {
    public static void main(String[] args) throws InterruptedException {
        var thread1 = new Thread(() -> {
            var ob1 = Singleton.getInstance();
            System.out.println(ob1);
        });
        var thread2 = new Thread(() -> {
            var ob2 = Singleton.getInstance();
            System.out.println(ob2);
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("done");
    }


    private static class Singleton {
        public static Singleton instance = null;

        private Singleton() {
        }

        public static Singleton getInstance() {
            if (instance == null) {
                create();
            }

            return instance;
        }

        private static synchronized void create() {
            if (instance == null) {
                instance = new Singleton();
            }
        }
    }
}

