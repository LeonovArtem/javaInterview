package leetCode;

import java.util.Arrays;
import java.util.stream.IntStream;

public class N_4_MedianTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        var arr = IntStream.concat(
                        Arrays.stream(nums1),
                        Arrays.stream(nums2)
                )
                .sorted()
                .toArray();

        var medianIndex = arr.length / 2 - 1;
        var nextMedianIndex = medianIndex + 1;
        if (arr.length % 2 == 0) {
            double sumMedina = arr[medianIndex] + arr[nextMedianIndex];

            return sumMedina / 2;
        }

        return arr[nextMedianIndex];
    }
}
