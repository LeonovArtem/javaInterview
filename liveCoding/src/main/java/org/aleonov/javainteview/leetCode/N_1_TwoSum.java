package org.aleonov.javainteview.leetCode;

import java.util.Arrays;
import java.util.List;

public class N_1_TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4};
        var target = 6;

        System.out.println(Arrays.toString(twoSumIndex(nums, target)));
    }

    // Отсортированный массив(Если нужно вернуть значения)
    public static int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            var expectedTwoNum = target - nums[i];
            var expectedSumIndex = Arrays.binarySearch(nums, i + 1, nums.length, expectedTwoNum);
            if (expectedTwoNum > 0) {
                return new int[]{nums[i], nums[expectedSumIndex]};
            }
        }

        throw new RuntimeException("bot found");
    }

    public static int[] twoSumIndex(int[] nums, int target) {
        List<Integer> list = Arrays.stream(nums).boxed().toList();
        Arrays.sort(nums);
        for (int i = 0; i < list.size(); i++) {
            var firstNum = nums[i];
            var expectedTwoNum = target - firstNum;
            var expectedSumIndex = Arrays.binarySearch(nums, i + 1, nums.length, expectedTwoNum);

            if (expectedSumIndex > 0) {
                return new int[]{list.lastIndexOf(firstNum), list.indexOf(expectedTwoNum)};
            }
        }


        throw new RuntimeException("Not found");
    }

}
