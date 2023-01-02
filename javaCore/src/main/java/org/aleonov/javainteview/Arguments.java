package org.aleonov.javainteview;

public class Arguments {
    public static void main(String[] args) {
        var env = System.getenv();
        System.out.printf(env.toString());
    }
}
