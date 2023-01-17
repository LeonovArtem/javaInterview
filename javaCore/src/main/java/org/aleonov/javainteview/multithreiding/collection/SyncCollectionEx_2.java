package org.aleonov.javainteview.multithreiding.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncCollectionEx_2 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> arrList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            arrList.add(i);
        }
        List<Integer> list = Collections.synchronizedList(arrList);

        Runnable runnable1 = getSyncRunnable1(list);

        Runnable runnable2 = () -> {
            // Тут ставится лок
            list.remove(10);
        };

        var thread1 = new Thread(runnable1);
        var thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(list);
    }

    private static Runnable getRunnable1(List<Integer> list) {
        Runnable runnable1 = () -> {
            // Когда мы используем иттератор в многопоточном приложении будет ConcurrentModificationException!
            var iterator = list.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        };
        return runnable1;
    }

    private static Runnable getSyncRunnable1(List<Integer> list) {
        Runnable runnable1 = () -> {
            synchronized (list){
                // Когда мы используем иттератор в многопоточном приложении будет ConcurrentModificationException!
                var iterator = list.iterator();
                while (iterator.hasNext()) {
                    System.out.println(iterator.next());
                }
            }
        };

        return runnable1;
    }
}
