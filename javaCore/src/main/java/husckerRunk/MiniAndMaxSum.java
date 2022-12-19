package husckerRunk;

import java.util.List;
import java.util.stream.Stream;

// todo: https://www.hackerrank.com/challenges/one-week-preparation-kit-mini-max-sum/problem
public class MiniAndMaxSum {
    public static void main(String[] args) {
        List<Integer> list1 = Stream
                .iterate(1, integer -> integer + 1)
                .limit(5)
                .toList();

        List<Integer> list2 = List.of(256741038, 623958417, 467905213, 714532089, 938071625);

        miniMaxSum(list1);
    }

    public static void miniMaxSum(List<Integer> arr) {
        var arrSum = arr.stream()
                .mapToLong(Integer::longValue)
                .sum();

        var minElement = arr.stream()
                .mapToLong(Integer::longValue)
                .min()
                .getAsLong();

        var maxElement = arr.stream()
                .mapToLong(Integer::longValue)
                .max()
                .getAsLong();

        var minSum = arrSum - maxElement;
        var maxSum = arrSum - minElement;

        System.out.println(minSum + " " + maxSum);

        System.out.println("Arr Sum: " + arrSum);
        System.out.println("Min sum: " + minSum);
        System.out.println("Max sum: " + maxSum);

    }
}
