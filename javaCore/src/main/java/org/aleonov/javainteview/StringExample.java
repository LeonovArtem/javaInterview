package org.aleonov.javainteview;

public class StringExample {
    public static void main(String[] args) {
        String s1 = "one";
        String s2 = "one";
        String newS3 = new String("one");
        String internS4 = "one".intern();


        System.out.println(s1 == s2);
        System.out.println(s1 == newS3);
        System.out.println(s1 == internS4);
    }
}
