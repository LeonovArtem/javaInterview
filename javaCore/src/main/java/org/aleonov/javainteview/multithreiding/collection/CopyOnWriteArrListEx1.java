package org.aleonov.javainteview.multithreiding.collection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrListEx1 {
    public static void main(String[] args) throws InterruptedException {
        var list = new CopyOnWriteArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");

        Runnable runnable1 = () -> {
            var iterator = list.iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(iterator.next());
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.remove(3);
            list.add("New element");
        };

        execute(list, runnable1, runnable2);
    }

    private static void execute(List<Object> list, Runnable runnable1, Runnable runnable2) throws InterruptedException {
        System.out.println(list);
        var thread1 = new Thread(runnable1);
        var thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(list);
    }
}
