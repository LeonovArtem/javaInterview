package org.aleonov.javainteview.codility;

public class TipicoCompany1 {
    public static void main(String[] args) {
        System.out.println(solution(4));
    }

    public static String solution(int N) {
        StringBuilder str = new StringBuilder();
        if (N % 2 == 1) {
            str.append("a".repeat(N));
        } else {
            str.append("a".repeat(N - 1));
            str.append('b');
        }
        return str.toString();
    }
}
