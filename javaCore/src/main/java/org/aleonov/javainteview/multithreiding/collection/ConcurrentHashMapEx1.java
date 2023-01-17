package org.aleonov.javainteview.multithreiding.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapEx1 {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> users = new ConcurrentHashMap<>();
        users.put(1, "Zaur");
        users.put(2, "Ivan");
        users.put(3, "Sergey");
        users.put(4, "Artem");
        users.put(5, "Lana");
        System.out.println(users);

        Runnable runnable = () -> {
            var iterator = users.keySet().iterator();
            while (iterator.hasNext()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                var key = iterator.next();
                System.out.println(key + ":" + users.get(key));
            }
        };

        Runnable runnable2 = () -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            users.put(6, "New User");
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(users);
    }
}
