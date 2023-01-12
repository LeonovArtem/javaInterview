package org.aleonov.javainteview.codility;

public class TipicoCompany2 {
    public static void main(String[] args) {
        System.out.println(solution(10, 21));
    }

    public static int solution(int A, int B) {
        int countSides = 4;
        int maxLen = (A + B) / countSides;
        while (maxLen > 0) {
            int maxLenA = A / maxLen;
            int maxLenB = B / maxLen;
            if (maxLenA + maxLenB >= countSides) {
                return maxLen;
            }

            maxLen--;
        }

        return 0;
    }
}
