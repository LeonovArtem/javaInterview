package org.aleonov.javainteview.multithreiding.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncCollectionEx_1 {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> source = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            source.add(i);
        }

        List<Integer> target = Collections.synchronizedList(new ArrayList<>());

        Runnable runnable = () -> {
            target.addAll(source);
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        // Результат может быть 10 или 20
        System.out.println(target.size());
    }
}
