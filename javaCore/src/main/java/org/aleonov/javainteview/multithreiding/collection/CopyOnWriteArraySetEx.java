package org.aleonov.javainteview.multithreiding.collection;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class CopyOnWriteArraySetEx {
    public static void main(String[] args) {
        Set<String> users = new CopyOnWriteArraySet<>();
        users.add("user_1");
        users.add("user_2");
        users.add("user_3");

        System.out.println(users);
    }
}
