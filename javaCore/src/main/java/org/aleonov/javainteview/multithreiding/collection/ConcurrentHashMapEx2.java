package org.aleonov.javainteview.multithreiding.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapEx2 {
    public static void main(String[] args) throws InterruptedException {
        Map<Integer, String> users = new ConcurrentHashMap<>();
        users.put(1, "Zaur");
        users.put(null, "New user");
        users.put(2, null);
        System.out.println(users);
    }
}
